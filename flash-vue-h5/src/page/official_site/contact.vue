<template>
  <div>
    <x-header title="联系我们"></x-header>
    <div>
      <img src="static/images/contact.png"  >
    </div>
    <group>
      <x-input title="姓名" v-model="form.userName" placeholder="姓名"></x-input>
      <x-input title="手机号码" v-model="form.mobile" placeholder="手机号码" keyboard="number" is-type="china-mobile"></x-input>
      <x-input title="邮箱" v-model="form.email" placeholder="邮箱" is-type="email"></x-input>
      <x-textarea title="留言" :max="200" v-model="form.remark" placeholder="留言"></x-textarea>
      <x-button type="primary" @click.native="submit" style="width:80%;">提交</x-button>
    </group>
    <br/><br/>
    <footMenu></footMenu>
  </div>
</template>
<script src="./contact.js">
</script>
