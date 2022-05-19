package tw.edu.pu.csim.cyyang.game

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Rect

class player(context: Context) {

    val res = context.resources  //讀取資源
    var x:Int = 0
    var y:Int = res.displayMetrics.heightPixels/2
    var w:Int
    var h:Int
    lateinit var player: Bitmap
    lateinit var SrcRect: Rect
    lateinit var DestRect: Rect
    var count : Int = 1

init {
    player = BitmapFactory.decodeResource(res, R.drawable.fly1)
    SrcRect = Rect(0, 0, player.width, player.height) //裁切
    w = player.width/4
    h = player.height/4
    y -= h/2
}

fun draw(canvas: Canvas) {
    DestRect = Rect(x, y, x + w, y + h)
    canvas.drawBitmap(player, SrcRect, DestRect, null)
}

fun update(){
    if (count==1){
        count = 2
        player = BitmapFactory.decodeResource(res, R.drawable.fly2)
    }
    else{
        count = 1
        player = BitmapFactory.decodeResource(res, R.drawable.fly1)
    }
}
}