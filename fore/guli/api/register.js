import request from '@/utils/request'
export default {
  // 发送验证码
  sendCode(phone) {
    return request({
      url: `/Edumsm/msm/send/${phone}`,
      method: 'get'
    })
  },
  // 注册的方法
  registerMember(userInfo) {
    return request({
      url: `/ucenterservice/ucenter-member/register`,
      method: 'post',
      data: userInfo
    })
  }
}
