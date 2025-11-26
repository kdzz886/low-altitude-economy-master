import { defineStore } from 'pinia'

export const useUserStore = defineStore('user', {
  state: () => ({
    userId: localStorage.getItem('userId') || '',
    token: localStorage.getItem('token') || '',
    username: localStorage.getItem('username') || ''
  }),
  actions: {
    setUser(userId: string, username: string, token?: string) {
      this.userId = userId
      this.username = username
      this.token = token || userId
      localStorage.setItem('userId', userId)
      localStorage.setItem('token', token || userId)
      localStorage.setItem('username', username)
    },
    logout() {
      this.userId = ''
      this.token = ''
      this.username = ''
      localStorage.clear()
    }
  }
})
