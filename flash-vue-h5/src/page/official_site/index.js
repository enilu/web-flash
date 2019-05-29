import {TransferDom, Panel, Swiper, SwiperItem, GroupTitle} from 'vux'
import footMenu from '../../components/footer/footMenu'
import menuGroup from '../../components/menu/menuGroup'
import productList from '../../components/product/productList'
import api from '../../fetch/api'
import {getApiUrl} from '../../util/tool'

export default {
  components: {
    TransferDom, footMenu, Panel, Swiper, SwiperItem, menuGroup, productList, GroupTitle
  },
  data () {
    return {
      productList: [],
      solutionList: [],
      newsList: [],
      banner: {}
    }
  },
  mounted () {
    this.init()
  },

  methods: {
    init () {
      const imgBase = getApiUrl() + '/file/getImgStream?idFile='
      api.getOffcialSite().then(res => {

        this.banner = res.data.data.banner
        for (const index in this.banner.list) {
          this.banner.list[index].img = imgBase + this.banner.list[index].idFile
        }
        this.newsList = res.data.data.newsList
        this.solutionList = res.data.data.solutionList
        this.productList = res.data.data.productList
        for (const index in this.solutionList) {
          this.solutionList[index].img = imgBase + this.solutionList[index].img
        }
        for (const index in this.productList) {
          this.productList[index].img = imgBase + this.productList[index].img
        }
      })
    },
    bannerChange (index) {
      this.banner.index = index
    },
    onItemClick (index) {
      this.getTopics(this.tabData[index].key)
    }
  }
}
