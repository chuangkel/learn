import axios from 'axios'
import global_ from './Global'
let base = "" ; //global_.base; 
let Authorization = localStorage.getItem("Authorization");

export const postRequest = (url, params) => {
  
  return axios({
    method: 'post',
    url: `${base}${url}`,
    data: params,
    transformRequest: [function (data) {
      let ret = ''
      for (let it in data) {
        ret += encodeURIComponent(it) + '=' + encodeURIComponent(data[it]) + '&'
      }
      return ret
    }],
    withCredentials: true,
    headers: {
      // 'Access-Control-Allow-Origin':'*'
      // ,
      'Content-Type': 'application/x-www-form-urlencoded',
      // "Access-Control-Expose-Headers":"Access-Control-*",
      // "Access-Control-Allow-Headers": "Access-Control-*, Origin, X-Requested-With, Content-Type, Accept",
      // 'Access-Control-Allow-Methods': 'GET, POST, PUT, DELETE, OPTIONS, HEAD',
      // 'Allow': 'GET, POST, PUT, DELETE, OPTIONS, HEAD',
    }
  });
}

export const postRequestJson = (url, params) => {
  return axios({
    method: 'post',
    url: `${base}${url}`,
    data: params,
    withCredentials: true,
    headers: {
      'Content-Type': 'application/json',
      'Authorization':`${Authorization}`
    }
  });
}

export const uploadFileRequest = (url, params) => {
  return axios({
    method: 'post',
    url: `${base}${url}`,
    data: params,
    headers: {
      'Content-Type': 'multipart/form-data',
      'Authorization':`${Authorization}`
    }
  });
}
export const putRequest = (url, params) => {
  return axios({
    method: 'put',
    url: `${base}${url}`,
    data: params,
    transformRequest: [function (data) {
      let ret = ''
      for (let it in data) {
        ret += encodeURIComponent(it) + '=' + encodeURIComponent(data[it]) + '&'
      }
      return ret
    }],
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded',
      'Authorization':`${Authorization}`
    }
  });
}
export const deleteRequest = (url) => {
  return axios({
    method: 'delete',
    url: `${base}${url}`
  });
}
export const getRequest = (url,params) => {
  return axios({
    method: 'get',
    data:params,
    transformRequest: [function (data) {
      let ret = ''
      for (let it in data) {
        ret += encodeURIComponent(it) + '=' + encodeURIComponent(data[it]) + '&'
      }
      return ret
    }],
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded',
      'Authorization':`${Authorization}`
    },
    url: `${base}${url}`
  });
}

export const getAllRequest = (url) => {
  debugger
  return axios({
    method: 'get',
    withCredentials: true,
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded',
      'Authorization':`${Authorization}`
    },
    url: `${base}${url}`
  });
}
