package tw.edu.pu.csim.cyyang.game

import android.content.pm.ActivityInfo
import android.graphics.Canvas
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    lateinit var img : ImageView
    lateinit var mysv : MySurfaceView
    var flag:Boolean = false
    lateinit var job : Job

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE)

        img = findViewById(R.id.img)
        mysv = findViewById(R.id.mysv)

        img.setOnClickListener({
            if (flag){
                flag = false
                img.setImageResource(R.drawable.start)
                job.cancel()
            }
            else{
                flag = true
                img.setImageResource(R.drawable.stop)
                job = GlobalScope.launch(Dispatchers.Main) {
                    while(flag) {
                        delay(10)
                        var canvas: Canvas = mysv.surfaceHolder.lockCanvas()
                        mysv.drawSomething(canvas)
                        mysv.surfaceHolder.unlockCanvasAndPost(canvas)
                    }
                }
            }

        })


    }
}