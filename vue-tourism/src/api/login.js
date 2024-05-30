import request from '@/utils/require.js'

// 用户
export const insertNewUser = (data) => {
  return request.post('/insertNewUser', data)
}

// 找回密码
export const SendEmail = (email, username) => {
  return request.get(`/SendEmail?email=${email}&username=${username}`)
}

export const UpdatePasswordByUsernmae = (token, password) => {
  return request.get(`/UpdatePasswordByUsernmae?token=${token}&password=${password}`)
}
