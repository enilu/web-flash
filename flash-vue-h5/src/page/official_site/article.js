import {TransferDom, XHeader} from 'vux'
import footMenu from '../../components/footer/footMenu'
import meArticle from '../../components/article/article'
import meReply from '../../components/reply/reply'
import api from '../../fetch/api'

export default {
  directives: {
    TransferDom
  },
  components: {
    footMenu, XHeader, meArticle, meReply
  },
  data () {
    return {
      showComponent: {
        reply: false
      },
      article: {title: '', author: {avatar_url: ''}},
      id: '',
      type: ''
    }
  },
  mounted () {
    this.init()
  },

  methods: {
    init () {
      this.id = this.$route.query.id
      this.type = this.$route.query.type
      api.getArticle(this.id, this.type).then(res => {
        console.log(res)
        this.article = res.data
      })
    },
    getTopic () {
      api.getArticle(this.id).then(res => {
        this.topic = res.data
        this.title = res.data.title
      })
    }
  }
}
