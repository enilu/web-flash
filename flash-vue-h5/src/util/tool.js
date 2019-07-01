import store from '../store/store'

const apiUrl = env.BASE_API

export function getApiUrl () {
  console.log(apiUrl)
  return apiUrl
}

export function setSession (key, value) {
  sessionStorage.setItem(key, value)
}

export function getSession (key) {
  return sessionStorage.getItem(key)
}

/**
 *   Toast公共方法
 */
export function toast (str, icon) {
  store.dispatch('showToast', true)
  if (icon == 'success') {
    store.dispatch('showSuccess', true)
    store.dispatch('showFail', false)
  } else {
    store.dispatch('showSuccess', false)
    store.dispatch('showFail', true)
  }
  store.dispatch('toastMsg', str)
  setTimeout(() => {
    store.dispatch('showToast', false)
  }, 1500)
}

/**
 * dialog公共方法
 */

export function alert (str) {
  store.dispatch('showAlert', true)
  store.dispatch('alertMsg', str)
  setTimeout(() => {
    store.dispatch('showAlert', false)
  }, 1500)
}

export function guid () {
  return 'xxxxxxxx-xxxx-xxxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function (c) {
    var r = Math.random() * 16 | 0, v = c == 'x' ? r : (r & 0x3 | 0x8)
    return v.toString(16)
  })
}

export function daysBetween (beginDate, endDate) {
  var timeslong = getDate(beginDate).getTime() - getDate(endDate).getTime()
  return (timeslong / (1000 * 60 * 60 * 24))
}

function getDate (strDate) {
  if (strDate == null || strDate === undefined) return null
  var date = new Date()
  try {
    if (strDate == undefined) {
      date = null
    } else if (typeof strDate == 'string') {
      strDate = strDate.replace(/:/g, '-')
      strDate = strDate.replace(/ /g, '-')
      var dtArr = strDate.split('-')
      if (dtArr.length >= 3 && dtArr.length < 6) {
        date = new Date(dtArr[0], dtArr[1], dtArr[2])
      } else if (date.length > 8) {
        date = new Date(Date.UTC(dtArr[0], dtArr[1] - 1, dtArr[2], dtArr[3] - 8, dtArr[4], dtArr[5]))
      }
    } else {
      date = strDate
    }

    return date
  } catch (e) {
    alert('格式化日期出现异常：' + e.message)
  }
}

Date.prototype.format = function (fmt) {
  var o = {
    'M+': this.getMonth() + 1,                 //月份
    'd+': this.getDate(),                    //日
    'h+': this.getHours(),                   //小时
    'm+': this.getMinutes(),                 //分
    's+': this.getSeconds(),                 //秒
    'q+': Math.floor((this.getMonth() + 3) / 3), //季度
    'S': this.getMilliseconds()             //毫秒
  }
  if (/(y+)/.test(fmt)) {
    fmt = fmt.replace(RegExp.$1, (this.getFullYear() + '').substr(4 - RegExp.$1.length))
  }
  for (var k in o) {
    if (new RegExp('(' + k + ')').test(fmt)) {
      fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (('00' + o[k]).substr(('' + o[k]).length)))
    }
  }
  return fmt
}

export function denseBankCard (bankCard) {
  if (bankCard) {
    return bankCard.substr(bankCard.length - 4)
  }
  return ''
}

export function denseMobile (mobile) {
  if (mobile) {
    return mobile.substr(mobile.length - 4)
  }
  return ''
}
