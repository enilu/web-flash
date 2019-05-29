import {XHeader, Panel, Swiper, SwiperItem} from 'vux'
import footMenu from '../../components/footer/footMenu'
import api from '../../fetch/api'
import {getApiUrl} from '../../util/tool'

export default {
  components: {
    XHeader, footMenu, Panel, Swiper, SwiperItem
  },
  data () {
    return {
      banner: {},
      newsList: [],
      userName: 'enilu'
    }
  },
  created () {
    this.init()
  },
  methods: {
    init () {

      const imgBase = getApiUrl() + '/file/getImgStream?idFile='
      api.getNewsList().then(res => {
        this.banner = res.data.data.banner
        for (const index in this.banner.list) {
          this.banner.list[index].img = imgBase + this.banner.list[index].idFile
        }
        this.list = res.data.data.list
      })
    },
    bannerChange (index) {
      this.banner.index = index
    }
  }
}
