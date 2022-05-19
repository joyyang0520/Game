package tw.edu.pu.csim.cyyang.game

import android.content.pm.ActivityInfo
import android.graphics.Canvas
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.AppGlideModule
import kotlinx.coroutines.*

@GlideModule
public final class MyAppGlideModule : AppGlideModule()

class MainActivity : AppCompatActivity() {

    lateinit var imgbtn : ImageView
    lateinit var mysv : MySurfaceView
    var flag:Boolean = false
    lateinit var job : Job
    lateinit var imgme : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE)

        imgbtn = findViewById(R.id.imgbtn)
        mysv = findViewById(R.id.mysv)

        imgme = findViewById(R.id.imgme)
        GlideApp.with(this)
            //.load(R.drawable.earth)
            .load(R.drawable.photo)
            .circleCrop()
            .override(800, 600)
            .into(imgme)

        imgbtn.setOnClickListener({
            if (flag){
                flag = false
                imgbtn.setImageResource(R.drawable.start)
                job.cancel()
            }
            else{
                flag = true
                imgbtn.setImageResource(R.drawable.stop)
                job = GlobalScope.launch(Dispatchers.Main) {
                    while(flag) {
                        delay(10)
                        mysv.player.update()
                       // mysv.player.update()
                        var canvas: Canvas = mysv.surfaceHolder.lockCanvas()
                        mysv.drawSomething(canvas)
                        mysv.surfaceHolder.unlockCanvasAndPost(canvas)
                    }
                }
            }

        })


    }
}