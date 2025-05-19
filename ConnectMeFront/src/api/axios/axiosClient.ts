import axios from 'axios';

export const axiosClient = axios.create({
  baseURL: 'http://dev.api.localhost',
  headers: { 'Content-Type': 'application/json' },
});
