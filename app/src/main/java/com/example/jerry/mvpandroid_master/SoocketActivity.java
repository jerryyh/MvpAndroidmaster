package com.example.jerry.mvpandroid_master;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import com.example.jerry.mvpandroid_master.databinding.ActivityIpcBinding;
import com.example.jerry.mvpandroid_master.service.TCPServerService;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by jerry on 2018/7/3.
 */
public class SoocketActivity extends Activity {
    private ActivityIpcBinding binding;
    private Socket mClientSocket;
    private PrintWriter mPrintWriter;
    private SocketHandler mSocketHandler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_ipc);
        binding.btSendSocket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMsgToSocketServer();
            }
        });
        bindSocketService();
    }

    private void bindSocketService() {
        //启动服务端
        Intent intent = new Intent(this, TCPServerService.class);
        startService(intent);
        mSocketHandler = new SocketHandler();
        new Thread(new Runnable() {    //新开一个线程连接、接收数据
            @Override
            public void run() {
                try {
                    connectSocketServer();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /**
     * 通过 Socket 连接服务端
     */
    private void connectSocketServer() throws IOException {
        Socket socket = null;
        while (socket == null) {    //选择在循环中连接是因为有时请求连接时服务端还没创建，需要重试
            try {
                socket = new Socket("localhost", 8688);
                mClientSocket = socket;
                mPrintWriter = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
            } catch (IOException e) {
                SystemClock.sleep(1_000);
            }
        }
        //连接成功
        mSocketHandler.sendEmptyMessage(SocketHandler.CODE_SOCKET_CONNECT);
        //获取输入流
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        while (!isFinishing()) {    //死循环监听服务端发送的数据
            final String msg = in.readLine();
            if (!TextUtils.isEmpty(msg)) {
                //数据传到 Handler 中展示
                mSocketHandler.obtainMessage(SocketHandler.CODE_SOCKET_MSG,
                        "\n" + getCurrentTime() + "\nserver : " + msg)
                        .sendToTarget();
            }
            SystemClock.sleep(1_000);
        }
        System.out.println("Client quit....");
        mPrintWriter.close();
        in.close();
        socket.close();
    }

    public void sendMsgToSocketServer() {
        final String msg = binding.etClientSocket.getText().toString();
        if (!TextUtils.isEmpty(msg) && mPrintWriter != null) {
            //发送数据，这里注意要在线程中发送，不能在主线程进行网络请求，不然就会报错
            new Thread(new Runnable() {
                @Override
                public void run() {
                    mPrintWriter.println(msg);
                }
            }).start();
            binding.etClientSocket.setText("");
            binding.tvSocketMessage.setText(binding.tvSocketMessage.getText() + "\n" + getCurrentTime() + "\nclient : " + msg);
        }
    }
    /**
     * 使用系统当前日期时间
     */
    public static String getCurrentTime() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return dateFormat.format(date);
    }

    /**
     * 处理 Socket 线程切换
     */
    @SuppressWarnings("HandlerLeak")
    public class SocketHandler extends Handler {
        public static final int CODE_SOCKET_CONNECT = 1;
        public static final int CODE_SOCKET_MSG = 2;

        @Override
        public void handleMessage(final Message msg) {
            switch (msg.what) {
                case CODE_SOCKET_CONNECT:
                    binding.btSendSocket.setEnabled(true);
                    break;
                case CODE_SOCKET_MSG:
                    binding.tvSocketMessage.setText(binding.tvSocketMessage.getText() + (String) msg.obj);
                    break;
            }
        }
    }
}
