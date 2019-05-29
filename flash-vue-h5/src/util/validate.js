
// 验证手机号
export function isPhoneNumber(mobile) {
  return true;
  //var myreg = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1})|145|147|149|173|175|176|177|178|198)+\d{8})$/;
  //if (!myreg.test(mobile)) {
   // return false;
  //} else {
   // return true
  //}
}

export function isMobilePasswd(passwd){
  var myreg = /\d{4,6}$/;
  if (!myreg.test(passwd)) {
  return false;
  } else {
  return true
  }
}

// 验证密码
export function isPassword(pass) {
  /**
   * //密码长度限定 if (str.length < 6 || str.length > 10){ return false; }
   * //中英文混合密码验证
   * if(!str.match(/[0-9]/)||!(str.match(/[a-zA-Z]/)||/\W/.test(str))){ return
	 * false; }
   */
  var val = pass;
  if (!val || val.length < 6 ) {
    return false
  }
  return true;
}

export function isIdCard(pId) {
  // 检查身份证号码
  pId = pId.toLowerCase();
  var arrVerifyCode = [ 1, 0, "x", 9, 8, 7, 6, 5, 4, 3, 2 ];
  var Wi = [ 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 ];
  var Checker = [ 1, 9, 8, 7, 6, 5, 4, 3, 2, 1, 1 ];

  // if(pId.length != 15 && pId.length != 18) return false;
  if (pId.length == 15 || pId.length != 18)
    return false;

  var Ai = pId.length == 18 ? pId.substring(0, 17) : pId.slice(0, 6) + "19"
    + pId.slice(6, 16);

  if (!/^\d+$/.test(Ai))
    return false;

  var yyyy = Ai.slice(6, 10), mm = Ai.slice(10, 12) - 1, dd = Ai
    .slice(12, 14);

  var d = new Date(yyyy, mm, dd), now = new Date();
  var year = d.getFullYear(), mon = d.getMonth(), day = d.getDate();

  if (year != yyyy || mon != mm || day != dd || d > now || year < 1940)
    return false;

  for (var i = 0, ret = 0; i < 17; i++)
    ret += Ai.charAt(i) * Wi[i];
  Ai += arrVerifyCode[ret %= 11];

  return pId.length == 18 && pId != Ai ? false : true;

}
