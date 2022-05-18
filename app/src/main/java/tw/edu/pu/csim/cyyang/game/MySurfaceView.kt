package tw.edu.pu.csim.cyyang.game

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.SurfaceHolder
import android.view.SurfaceView


class MySurfaceView(context: Context?, attrs: AttributeSet?) : SurfaceView(context, attrs),
SurfaceHolder.Callback{

    var surfaceHolder: SurfaceHolder
    var BG: Bitmap
    var BGmove:Int = 0

    init {
        surfaceHolder = getHolder()
        BG = BitmapFactory.decodeResource(getResources(), R.drawable.background)
        //btnStart = BitmapFactory.decodeResource(getResources(), R.drawable.start)
        surfaceHolder.addCallback(this)

    }

    override fun surfaceCreated(p0: SurfaceHolder) {
        var canvas: Canvas = surfaceHolder.lockCanvas()
        drawSomething(canvas)
        surfaceHolder.unlockCanvasAndPost(canvas)

    }

    override fun surfaceChanged(p0: SurfaceHolder, p1: Int, p2: Int, p3: Int) {

    }

    override fun surfaceDestroyed(p0: SurfaceHolder) {

    }

    fun drawSomething(canvas:Canvas) {
        //canvas.drawBitmap(BG, 0f, 0f, null)
        var SrcRect:Rect = Rect(0, 0,BG.width, BG.height)
        var w:Int = width
        var h:Int = height
        var DestRect: Rect = Rect(0, 0, w, h)
        canvas.drawBitmap (BG, SrcRect, DestRect, null)

        BGmove -= 5
        var BGnew:Int = w + BGmove

        if (BGnew <= 0) { //當圖片捲完時 重新開始
            BGmove = 0
            canvas.drawBitmap(BG, SrcRect, DestRect, null)

        } else {
            DestRect = Rect(BGmove, 0, BGmove+w, h)
            canvas.drawBitmap(BG, SrcRect, DestRect, null)
            DestRect = Rect(BGnew, 0, BGnew+w, h)
            canvas.drawBitmap(BG, SrcRect, DestRect, null)
        }

        val paint = Paint(Paint.ANTI_ALIAS_FLAG)
        paint.color = Color.BLUE
        paint.textSize = 50f
        canvas.drawText("射擊遊戲(作者：楊喬茵)",50f,50f,paint)

    }
}