<template>
  <div
    :style="`width:${width};height: ${height};`"
    ref="map"
  ></div>
</template>

<script>
const { BMap }  = window;
export default {
  props: {
    width: {
      required: false,
      default: '100%',
    },
    height: {
      required: true,
    },
    pos: {
      type: Array,
      required: false,
      default: [],
    }
  },
  data() {
    return {
      map: null,
      marker: null,
    }
  },
  mounted() {
    this.init()
  },
  watch: {
    pos(pos) {
      if (pos) {
        this.transGPS(pos)
      }
    }
  },
  methods: {
    init() {
      this.map = new BMap.Map(this.$refs.map);
      const point = new BMap.Point(116.404, 39.915);  // 创建点坐标  
      this.map.centerAndZoom(point, 15); 
      this.map.enableScrollWheelZoom(true);
    },
    transGPS(pos) {
      // 坐标转换
      const point = new BMap.Point(pos[0], pos[1]);
      const convertor = new BMap.Convertor();
      convertor.translate([point], 1, 5, this.createMark)
    },
    createMark(data) {
      if(data.status === 0) {
        if (this.marker) {
          this.map.removeOverlay(this.marker);
          this.marker = null
        }
        this.marker = new BMap.Marker(data.points[0]);
        this.map.addOverlay(this.marker);
        var label = new BMap.Label(this.pos,{offset:new BMap.Size(20,-10)});
        this.marker.setLabel(label); //添加百度label
        this.map.setCenter(data.points[0]);
      }
    },
  }
}
</script>

