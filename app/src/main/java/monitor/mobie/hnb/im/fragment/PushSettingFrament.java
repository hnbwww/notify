package monitor.mobie.hnb.im.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.support.annotation.RequiresApi;

import monitor.mobie.hnb.im.R;
import monitor.mobie.hnb.im.utils.ServerJiangUtils;
import monitor.mobie.hnb.im.utils.ServerUrlUtils;

import monitor.mobie.hnb.im.utils.ToastUtils;
import monitor.mobie.hnb.im.utils.WXUtils;

/**
 * Created by hdy on 2019/2/18.
 * 推送通知设置fragment
 */

public class PushSettingFrament extends PreferenceFragment {
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getPreferenceManager().setSharedPreferencesName("data");
        getPreferenceManager().setSharedPreferencesMode(Context.MODE_MULTI_PROCESS);
        addPreferencesFromResource(R.xml.preference_push);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void init() {
        final SharedPreferences shp = getPreferenceManager().getSharedPreferences();

        final EditTextPreference SCKEY = (EditTextPreference) findPreference("SCKEY");

        final EditTextPreference wx_corpid = (EditTextPreference) findPreference("wx_corpid");
        final EditTextPreference wx_corpsecret = (EditTextPreference) findPreference("wx_corpsecret");
        final EditTextPreference wx_agentid = (EditTextPreference) findPreference("wx_agentid");

        final EditTextPreference PUSHURL = (EditTextPreference) findPreference("PUSHURL");
        final EditTextPreference PUSHURL_ID = (EditTextPreference) findPreference("PUSHURL_ID");
        final EditTextPreference PUSHURL_KEY = (EditTextPreference) findPreference("PUSHURL_KEY");





        Preference sckey_enable = findPreference("SCKEY_enable");
        Preference wx_enable = findPreference("wx_enable");
        Preference PUSHURL_enable = findPreference("PUSHURL_enable");


        SCKEY.setSummary(getValue(shp.getString("SCKEY", "当前为空")));
        wx_corpid.setSummary(getValue(shp.getString("wx_corpid", "当前为空")));
        wx_corpsecret.setSummary(getValue(shp.getString("wx_corpsecret", "当前为空")));
        wx_agentid.setSummary(getValue(shp.getString("wx_agentid", "当前为空")));

        PUSHURL.setSummary(getValue(shp.getString("PUSHURL", "当前为空")));
        PUSHURL_ID.setSummary(getValue(shp.getString("PUSHURL_ID", "当前为空")));
        PUSHURL_KEY.setSummary(getValue(shp.getString("PUSHURL_KEY", "当前为空")));

        findPreference("sckey_test").setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                String sckey = shp.getString("SCKEY", "");
                if (!sckey.isEmpty()) {
                    ServerJiangUtils.sendTest(sckey);
                    ToastUtils.toast(PushSettingFrament.this.getContext(),"发送测试完成");
                } else {
                    ToastUtils.toast(PushSettingFrament.this.getContext(),"请输入SCKEY");
                }
                return true;
            }
        });

        findPreference("push_test").setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                String PUSHURL = shp.getString("PUSHURL", "");
                String PUSHURL_ID = shp.getString("PUSHURL_ID", "");
                String PUSHURL_KEY = shp.getString("PUSHURL_KEY", "");
                if (!PUSHURL.isEmpty() && !PUSHURL_ID.isEmpty() && !PUSHURL_KEY.isEmpty()) {
                    ServerUrlUtils.sendTest(PUSHURL, PUSHURL_ID, PUSHURL_KEY);
                    ToastUtils.toast(PushSettingFrament.this.getContext(),"发送测试完成");
                } else {
                    ToastUtils.toast(PushSettingFrament.this.getContext(),"请填写完整数据!");
                }
                return true;
            }
        });

        findPreference("wx_test").setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                String wxCorpid = shp.getString("wx_corpid", "");
                String wxCorpsecret = shp.getString("wx_corpsecret", "");
                String wxAgentid = shp.getString("wx_agentid", "");
                if (!wxCorpid.isEmpty() && !wxCorpsecret.isEmpty() && !wxAgentid.isEmpty()) {
                    WXUtils.sendTest(wxCorpid, wxCorpsecret, wxAgentid);
                    ToastUtils.toast(PushSettingFrament.this.getContext(),"发送测试完成");
                } else {
                    ToastUtils.toast(PushSettingFrament.this.getContext(),"请填写完整数据!");
                }
                return true;
            }
        });

        PUSHURL_enable.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                return true;
            }
        });

        sckey_enable.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                return true;
            }
        });
        wx_enable.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                return true;
            }
        });

        SCKEY.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                if (newValue.toString().isEmpty()) {
                    SCKEY.setSummary("当前为空");
                } else {
                    SCKEY.setSummary(newValue.toString());
                }
                return true;
            }
        });
        wx_corpid.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                if (newValue.toString().isEmpty()) {
                    wx_corpid.setDefaultValue("当前为空");
                } else {
                    wx_corpid.setSummary(newValue.toString());
                }
                return true;
            }
        });
        wx_corpsecret.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                if (newValue.toString().isEmpty()) {
                    wx_corpsecret.setSummary("当前为空");
                } else {
                    wx_corpsecret.setSummary(newValue.toString());
                }
                return true;
            }
        });
        wx_agentid.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                if (newValue.toString().isEmpty()) {
                    wx_agentid.setSummary("当前为空");
                } else {
                    wx_agentid.setSummary(newValue.toString());
                }
                return true;
            }
        });



        PUSHURL.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                if (newValue.toString().isEmpty()) {
                    PUSHURL.setDefaultValue("当前为空");
                } else {
                    PUSHURL.setSummary(newValue.toString());
                }
                return true;
            }
        });
        PUSHURL_ID.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                if (newValue.toString().isEmpty()) {
                    PUSHURL_ID.setSummary("当前为空");
                } else {
                    PUSHURL_ID.setSummary(newValue.toString());
                }
                return true;
            }
        });
        PUSHURL_KEY.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                if (newValue.toString().isEmpty()) {
                    PUSHURL_KEY.setSummary("当前为空");
                } else {
                    PUSHURL_KEY.setSummary(newValue.toString());
                }
                return true;
            }
        });


    }

    private String getValue(String get) {
        if (get.isEmpty()) {
            return "当前为空";
        }
        return get;
    }
}
