package monitor.mobie.hnb.im.broadcastreciver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import monitor.mobie.hnb.im.service.MonitorService;

/**
 * Created by hnb on 2017/9/10.
 */

public class BootBroadcastReciver extends BroadcastReceiver {
    private MonitorService monitorService;
    private boolean flag;

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent serviceIntent = new Intent(context, MonitorService.class);
        context.startService(serviceIntent);
    }
}
