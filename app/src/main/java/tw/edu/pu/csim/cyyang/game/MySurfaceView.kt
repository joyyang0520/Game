package tw.edu.pu.csim.cyyang.game

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView


class MySurfaceView(context: Context?, attrs: AttributeSet?) : SurfaceView(context, attrs),
    //SurfaceHolder.Callback{
    SurfaceHolder.Callback, GestureDetector.OnGestureListener{

    var surfaceHolder: SurfaceHolder
    var BG: Bitmap
    var BGmove:Int = 0
    var player:player
    var gDetector: GestureDetector

    init {
        surfaceHolder = getHolder()
        BG = BitmapFactory.decodeResource(getResources(), R.drawable.background)
        //btnStart = BitmapFactory.decodeResource(getResources(), R.drawable.start)
        surfaceHolder.addCallback(this)
        player = player(context!!)
        gDetector = GestureDetector(context, this)

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

        player.draw(canvas)
    }

    override fun onDown(p0: MotionEvent?): Boolean {
        return true
    }

    override fun onShowPress(p0: MotionEvent?) {

    }

    override fun onSingleTapUp(p0: MotionEvent?): Boolean {
        return true
    }
    override fun onScroll(e1: MotionEvent?, e2: MotionEvent?, p2: Float, p3: Float): Boolean {
        player.y = e2!!.y.toInt() - player.h/2
        return true
    }

    override fun onLongPress(p0: MotionEvent?) {

    }

    override fun onFling(p0: MotionEvent?, p1: MotionEvent?, p2: Float, p3: Float): Boolean {
        return true
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        gDetector.onTouchEvent(event)
        return true
    }
}