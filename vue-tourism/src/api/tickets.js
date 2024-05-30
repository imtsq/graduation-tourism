import request from '@/utils/require.js'

// 用户
export const InsertNewTickets = (data) => {
  return request.post('/InsertNewTickets', data)
}

export const SelectAllTicketsByorderKey = (data) => {
  return request.get(`/SelectAllTicketsByorderKey?orderkey=${data}`)
}

export const UpdateTicketsImg = (data) => {
  return request.post('/UpdateTicketsImg', data)
}

export const DeleteById = (data) => {
  return request.post('/DeleteById', data)
}

export const updateTickets = (data) => {
  return request.post('/updateTickets', data)
}

export const SelectTicketById = (id) => {
  return request.get(`/SelectTicketById?id=${id}`)
}
