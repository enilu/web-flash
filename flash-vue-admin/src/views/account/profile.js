
export default {
  data() {
    return {
      activeName: 'profile',
      user:{}
    }
  },
  mounted() {
    this.init()
  },
  methods: {
    init(){
      this.user = this.$store.state.user.profile
    },
    handleClick(tab, event){
      this.$router.push({ path: '/account/'+tab.name})
    }

  }
}
