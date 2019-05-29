import {XHeader, TransferDom, Panel, Swiper, SwiperItem, GroupTitle} from 'vux'
import footMenu from '../../components/footer/footMenu'
import menuGroup from '../../components/menu/menuGroup'
import productList from '../../components/product/productList'
import api from '../../fetch/api'
import {getApiUrl} from '../../util/tool'

export default {
  components: {
    XHeader, TransferDom, footMenu, Panel, Swiper, SwiperItem, menuGroup, productList, GroupTitle
  },
  data () {
    return {
      caseList: [],
      banner: {}
    }
  },
  mounted () {
    this.init()
  },

  methods: {
    init () {
      api.getCaseList().then(res => {
        const imgBase = getApiUrl() + '/file/getImgStream?idFile='
        this.banner = res.data.data.banner
        for (const index in this.banner.list) {
          this.banner.list[index].img = imgBase + this.banner.list[index].idFile
        }
        this.caseList = res.data.data.caseList
        for (const index in this.caseList) {
          this.caseList[index].img = imgBase + this.caseList[index].img
        }
      })
    },
    bannerChange (index) {
      this.banner.index = index
    },
    onItemClick (index) {
      this.getTopics(this.tabData[index].key)
    },
  }
}
