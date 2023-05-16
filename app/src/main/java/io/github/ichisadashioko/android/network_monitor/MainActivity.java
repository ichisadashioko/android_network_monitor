package io.github.ichisadashioko.android.network_monitor;

import android.app.Activity;
import android.content.Context;
import android.net.TrafficStats;
import android.os.Bundle;
import android.view.View;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkRequest;
import android.widget.EditText;

public class MainActivity extends Activity {
    public static long LAST_TOTAL_RX_BYTES = -1;
    public static long LAST_TOTAL_TX_BYTES = -1;
    public static long LAST_MOBILE_RX_BYTES = -1;
    public static long LAST_MOBILE_TX_BYTES = -1;
    public static long LAST_RECORD_TIME_MILLIS = -1;

    public EditText edit_text_total_rx_bytes;
    public EditText edit_text_total_tx_bytes;
    public EditText edit_text_mobile_rx_bytes;
    public EditText edit_text_mobile_tx_bytes;

    public EditText edit_text_rx_bytes_per_sec;
    public EditText edit_text_tx_bytes_per_sec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edit_text_total_rx_bytes = (EditText)findViewById(R.id.edit_text_total_rx_bytes);
        edit_text_total_tx_bytes = (EditText)findViewById(R.id.edit_text_total_tx_bytes);
        edit_text_mobile_rx_bytes = (EditText)findViewById(R.id.edit_text_mobile_rx_bytes);
        edit_text_mobile_tx_bytes = (EditText)findViewById(R.id.edit_text_mobile_tx_bytes);

        edit_text_rx_bytes_per_sec = (EditText)findViewById(R.id.edit_text_rx_bytes_per_sec);
        edit_text_tx_bytes_per_sec = (EditText)findViewById(R.id.edit_text_tx_bytes_per_sec);
    }

    public void on_toggle_monitor_thread_button_clicked(View view){
        try {
            long current_time_millis = System.currentTimeMillis();
            long total_received_bytes_since_boot = TrafficStats.getTotalRxBytes();
            long total_sent_bytes_since_boot = TrafficStats.getTotalTxBytes();
            long total_mobile_received_bytes_since_boot = TrafficStats.getMobileRxBytes();
            long total_mobile_sent_bytes_since_boot = TrafficStats.getMobileTxBytes();

            if(LAST_RECORD_TIME_MILLIS < 0){
                LAST_RECORD_TIME_MILLIS = current_time_millis;
                LAST_TOTAL_RX_BYTES = total_received_bytes_since_boot;
                LAST_TOTAL_TX_BYTES = total_sent_bytes_since_boot;
                LAST_MOBILE_RX_BYTES = total_mobile_received_bytes_since_boot;
                LAST_MOBILE_TX_BYTES = total_mobile_sent_bytes_since_boot;
            }



        }catch(Exception ex){
            ex.printStackTrace(System.err);
        }
    }
}
