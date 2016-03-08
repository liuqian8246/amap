package com.tboys.expressdelivery.fragment;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.AssetManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.tboys.expressdelivery.R;
import com.tboys.expressdelivery.adapter.ListViewAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class NearByFragment extends Fragment {

    private static final String TAG = "NearByFragment";
    private ListView listView;
    private TextView textView_start;
    private TextView textView_end;
    private ListViewAdapter adapter;
    private ArrayList<String> list = new ArrayList<>();
    private Callback callback;

    // 判断是否有区
    private boolean hasArea = false;

    // 省份
    private String[] mProvinceDatas;
    // 城市
    private String[] mCitiesDatas;
    // 地区
    private String[] mAreaDatas;

    // 列表选择的省市区
    private String selectedPro = "";
    private String selectedCity = "";
    private String selectedArea = "";

    private Spinner spinnerProvice;
    private Spinner spinnerCity;
    private Spinner spinnerCountry;

    private ArrayAdapter<String> proviceAdapter;
    private ArrayAdapter<String> cityAdapter;
    private ArrayAdapter<String> countryAdapter;


    // 存储省对应的所有市
    private Map<String, String[]> mCitiesDataMap = new HashMap<>();
    // 存储市对应的所有区
    private Map<String, String[]> mAreaDataMap = new HashMap<>();


    private boolean price;
    private boolean time;
    private boolean show;


    public NearByFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_near_by, container, false);
        initView(v);
        return v;
    }


    private void initView(View v) {
        listView = (ListView) v.findViewById(R.id.listView);
        textView_start = (TextView) v.findViewById(R.id.textView_start);
        textView_end = (TextView) v.findViewById(R.id.textView_end);

        textView_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(getContext(), Main2Activity.class);
//                startActivity(intent);
            }
        });


        textView_end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View vi = LayoutInflater.from(getContext()).inflate(R.layout.destination, null);
                spinnerProvice = (Spinner) vi.findViewById(R.id.spinner_country);
                spinnerCountry = (Spinner) vi.findViewById(R.id.spinner_less);
                spinnerCity = (Spinner) vi.findViewById(R.id.spinner_litter);
                BeginJsonCitisData(InitData());
                proviceAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, mProvinceDatas);
                proviceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerProvice.setAdapter(proviceAdapter);
                // 省份选择
                spinnerProvice.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        selectedPro = "";
                        selectedPro = (String) parent.getSelectedItem();
                        // 根据省份更新城市区域信息
                        updateCity(selectedPro);
                        Log.d(TAG, "mProvinceSpinner has excuted !" + "selectedPro is " + selectedPro);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                });

                // 市选择
                spinnerCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        selectedCity = "";
                        selectedCity = (String) parent.getSelectedItem();
                        updateArea(selectedCity);
                        Log.d(TAG, "mCitySpinner has excuted !" + "selectedCity is " + selectedCity);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

                // 区选择
                spinnerCountry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        selectedArea = "";
                        selectedArea = (String) parent.getSelectedItem();
//                        tv_address.setText("已选择: " + selectedPro + selectedCity + selectedArea);
                        Log.d(TAG, "mAreaSpinner has excuted !" + "selectedArea is " + selectedArea);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
                new AlertDialog.Builder(getContext())
                        .setTitle("选择目的地")
                        .setView(vi)
                        .setNegativeButton("取消", null)
                        .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                show = true;
                                adapter = new ListViewAdapter(getContext(), list, show);
                                listView.setAdapter(adapter);
                            }
                        })
                        .show();

            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        time = callback.getTime();
        price = callback.getPrice();
        list = callback.setData();
        //这里排序
        Log.d("222", String.valueOf(list.size()));

        adapter = new ListViewAdapter(getContext(), list, show);
        listView.setAdapter(adapter);
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    public interface Callback {
        ArrayList<String> setData();

        boolean getPrice();

        boolean getTime();
    }

    /**
     * 根据市 选择对应的区
     *
     * @Title: updateArea
     * @param @param
     *            city
     * @return void
     * @throws @date
     *             [2015年8月21日 下午3:52:17]
     */
    private void updateArea(String city) {

        String[] areas = mAreaDataMap.get(city);

        // 存在区
        if (areas != null) {
            // 存在区
            spinnerCountry.setVisibility(View.VISIBLE);
            countryAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, areas);
            countryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerCountry.setAdapter(countryAdapter);
            countryAdapter.notifyDataSetChanged();
            spinnerCountry.setSelection(0);
        } else {
            spinnerCountry.setVisibility(View.GONE);
        }

    }

    /**
     * 根据省份更新城市数据
     *
     * @Title: updateCityAndAreaData
     * @param @param
     *            pro 省份
     * @return void
     * @throws @date
     *             [2015年8月21日 下午3:20:15]
     */
    private void updateCity(String pro) {

        String[] cities = mCitiesDataMap.get(pro);
        for (int i = 0; i < cities.length; i++) {
            // 存在区
            cityAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, cities);
            cityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerCity.setAdapter(cityAdapter);
            cityAdapter.notifyDataSetChanged();
            spinnerCity.setSelection(0);
        }

    }

    /**
     * 开始解析城市数据
     *
     * @Title: BeginJsonCitisData
     * @param
     * @return void
     * @throws @date
     *             [2015年8月21日 上午10:02:23]
     */
    private void BeginJsonCitisData(String cityJson) {
        if (!TextUtils.isEmpty(cityJson)) {
            try {
                JSONObject object = new JSONObject(cityJson);
                JSONArray array = object.getJSONArray("citylist");

                // 获取省份的数量
                mProvinceDatas = new String[array.length()];
                String mProvinceStr = null;
                // 循环遍历
                for (int i = 0; i < array.length(); i++) {

                    // 循环遍历省份，并将省保存在mProvinceDatas[]中
                    JSONObject mProvinceObject = array.getJSONObject(i);
                    if (mProvinceObject.has("p")) {
                        mProvinceStr = mProvinceObject.getString("p");
                        mProvinceDatas[i] = mProvinceStr;
                    } else {
                        mProvinceDatas[i] = "unknown province";
                    }

                    JSONArray cityArray;
                    String mCityStr = null;
                    try {
                        // 循环遍历省对应下的城市
                        cityArray = mProvinceObject.getJSONArray("c");
                    } catch (Exception e) {
                        e.printStackTrace();
                        continue;
                    }

                    mCitiesDatas = new String[cityArray.length()];
                    for (int j = 0; j < cityArray.length(); j++) {
                        // 循环遍历城市，并将城市保存在mCitiesDatas[]中
                        JSONObject mCityObject = cityArray.getJSONObject(j);
                        if (mCityObject.has("n")) {
                            mCityStr = mCityObject.getString("n");
                            mCitiesDatas[j] = mCityStr;
                        } else {
                            mCitiesDatas[j] = "unknown city";
                        }

                        // 循环遍历地区
                        JSONArray areaArray;

                        try {
                            // 判断是否含有第三级区域划分，若没有，则跳出本次循环，重新执行循环遍历城市
                            areaArray = mCityObject.getJSONArray("a");
                        } catch (Exception e) {
                            e.printStackTrace();
                            continue;
                        }

                        // 执行下面过程则说明存在第三级区域
                        mAreaDatas = new String[areaArray.length()];
                        for (int m = 0; m < areaArray.length(); m++) {

                            // 循环遍历第三级区域，并将区域保存在mAreaDatas[]中
                            JSONObject areaObject = areaArray.getJSONObject(m);
                            if (areaObject.has("s")) {
                                mAreaDatas[m] = areaObject.getString("s");
                            } else {
                                mAreaDatas[m] = "unknown area";
                            }
                            Log.d(TAG, mAreaDatas[m]);
                        }

                        // 存储市对应的所有第三级区域
                        mAreaDataMap.put(mCityStr, mAreaDatas);
                    }

                    // 存储省份对应的所有市
                    mCitiesDataMap.put(mProvinceStr, mCitiesDatas);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * 从asset目录下读取城市json文件转化为String类型
     *
     * @Title: InitData
     * @param
     * @return void
     * @throws @date
     *             [2015年8月21日 上午9:40:00]
     */
    private String InitData() {
        StringBuffer sb = new StringBuffer();
        AssetManager mAssetManager = getActivity().getAssets();
        try {
            InputStream is = mAssetManager.open("city.json");
            byte[] data = new byte[is.available()];
            int len = -1;
            while ((len = is.read(data)) != -1) {
                sb.append(new String(data, 0, len, "utf-8"));
            }
            is.close();
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();

        }
        return null;
    }

}
