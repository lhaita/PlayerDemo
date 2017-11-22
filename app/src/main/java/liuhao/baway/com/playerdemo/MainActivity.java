package liuhao.baway.com.playerdemo;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;
import com.superplayer.library.SuperPlayer;

public class MainActivity extends AppCompatActivity implements SuperPlayer.OnNetChangeListener{

    private SuperPlayer sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sp = (SuperPlayer) findViewById(R.id.view_super_player);

        sp.setNetChangeListener(true)//设置是否见监听手机网络
                .setOnNetChangeListener(this)//设置网络监听的回调
                .onPrepared(new SuperPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared() {
                        /**
                         * 监听视频是否已经准备完成开始播放。（可以在这里处理视频封面的显示跟隐藏）
                         */
                    }
                }).onComplete(new Runnable() {
            @Override
            public void run() {
                /**
                 * 监听视频是否已经播放完成了。（可以在这里处理视频播放完成进行的操作）
                 */
            }
        }).onInfo(new SuperPlayer.OnInfoListener() {
            @Override
            public void onInfo(int what, int extra) {
                /**
                 * 监听视频的相关信息。
                 */

            }
        }).onError(new SuperPlayer.OnErrorListener() {
            @Override
            public void onError(int what, int extra) {
                /**
                 * 监听视频播放失败的回调
                 */

            }
        }).setTitle("我的视频")//设置视频的titleName
                .play("http://2449.vod.myqcloud.com/2449_bfbbfa3cea8f11e5aac3db03cda99974.f20.mp4");//开始播放视频
        sp.setScaleType(SuperPlayer.SCALETYPE_FITXY);
        sp.setPlayerWH(0,sp.getMeasuredHeight());//设置竖屏的时候屏幕的高度，如果不设置会切换后按照16:9的高度重置
    }

    @Override
    public void onWifi() {
        Toast.makeText(this,"当前网络环境是WIFI",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onMobile() {
        Toast.makeText(this,"当前网络环境是手机网络",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDisConnect() {
        Toast.makeText(this,"网络链接断开",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNoAvailable() {
        Toast.makeText(this,"无网络链接",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(sp!=null){
            sp.onPause();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(sp!=null){
            sp.onDestroy();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(sp!=null){
            sp.onResume();
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if(sp!=null){
            sp.onConfigurationChanged(newConfig);
        }
    }

    @Override
    public void onBackPressed() {
        if (sp != null && sp.onBackPressed()) {
            return;
        }
        super.onBackPressed();
    }
}
