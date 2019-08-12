package cn.qiuxiang.react.amap3d.maps

import android.content.Context
import android.graphics.Color
import cn.qiuxiang.react.amap3d.toLatLngList
import com.amap.api.maps.AMap
import com.amap.api.maps.model.LatLng
import com.amap.api.maps.model.Polyline
import com.amap.api.maps.model.PolylineOptions
import com.facebook.react.bridge.ReadableArray
import com.facebook.react.views.view.ReactViewGroup

import com.amap.api.maps.model.BitmapDescriptor
import com.amap.api.maps.model.BitmapDescriptorFactory

class AMapPolyline(context: Context) : ReactViewGroup(context), AMapOverlay {
    var polyline: Polyline? = null
        private set

    private var coordinates: ArrayList<LatLng> = ArrayList()
    private var colors: ArrayList<Int> = ArrayList()

    var width: Float = 1f
        set(value) {
            field = value
            polyline?.width = value
        }

    var color: Int = Color.BLACK
        set(value) {
            field = value
            polyline?.color = value
        }

    var zIndex: Float = 0f
        set(value) {
            field = value
            polyline?.zIndex = value
        }

    var geodesic: Boolean = false
        set(value) {
            field = value
            polyline?.isGeodesic = value
        }

    var dashed: Boolean = false
        set(value) {
            field = value
            polyline?.isDottedLine = value
        }

    var gradient: Boolean = false

    fun setCoordinates(coordinates: ReadableArray) {
        this.coordinates = coordinates.toLatLngList()
        polyline?.points = this.coordinates
    }

    fun setColors(colors: ReadableArray) {
        this.colors = ArrayList((0 until colors.size()).map { colors.getInt(it) })
    }
 /**
     * Created by adminZPH on 2017/4/14.
     * 设置线条中的纹理的方法
     * @return PolylineOptions
     * 
    public static PolylineOptions GetPolylineOptions(){
        //添加纹理图片
        val drawable = context.resources.getIdentifier(image, "drawable", context.packageName)
        List<BitmapDescriptor> textureList = new ArrayList<BitmapDescriptor>();
        BitmapDescriptor mRedTexture = BitmapDescriptorFactory
                .fromResource(R.mipmap.icon_road_white_arrow);
        BitmapDescriptor mBlueTexture = BitmapDescriptorFactory
                .fromResource(R.mipmap.icon_road_white_arrow);
        BitmapDescriptor mGreenTexture = BitmapDescriptorFactory
                .fromResource(R.mipmap.icon_road_white_arrow);
        textureList.add(mRedTexture);
        textureList.add(mBlueTexture);
        textureList.add(mGreenTexture);
        // 添加纹理图片对应的顺序
        List<Integer> textureIndexs = new ArrayList<Integer>();
        textureIndexs.add(0);
        textureIndexs.add(1);
        textureIndexs.add(2);
        PolylineOptions polylienOptions=new PolylineOptions();
        polylienOptions.setCustomTextureList(textureList);
        polylienOptions.setCustomTextureIndex(textureIndexs);
        polylienOptions.setUseTexture(true);
        polylienOptions.width(7.0f);
        return polylienOptions;
    }

*/
    override fun add(map: AMap) {
        //添加纹理图片
        var mipmap = context.resources.getIdentifier("icon_road_white_arrow", "mipmap", context.packageName)
        var textureList:ArrayList<BitmapDescriptor> = ArrayList();
        var mWhiteTexture:BitmapDescriptor = BitmapDescriptorFactory.fromResource(mipmap);
        textureList.add(mWhiteTexture);
        // 添加纹理图片对应的顺序
        var textureIndexs:List<Integer> = ArrayList();


        polyline = map.addPolyline(PolylineOptions()
                //.setCustomTextureList(textureList)
                .setUseTexture(true)
                .addAll(coordinates)
                .color(color)
                .colorValues(colors)
                .width(width)
                .useGradient(gradient)
                .geodesic(geodesic)
                .setDottedLine(dashed)
                .zIndex(zIndex))
    }

    override fun remove() {
        polyline?.remove()
    }
}
