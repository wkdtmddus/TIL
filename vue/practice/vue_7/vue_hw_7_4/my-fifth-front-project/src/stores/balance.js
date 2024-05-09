import { ref } from 'vue'
import { defineStore } from "pinia"

export const useBalanceStore = defineStore('balances', () => {
  const balances = ref([
    {
      name: '김하나',
      balance: 100000
      },
      {
      name: '김두리',
      balance: 10000
      },
      {
      name: '김서이',
      balance: 100
      }
  ])
  const findBalance = function (name) {
    for (const balance of balances.value) {
      if (balance.name === name) {
        return balance
      }
    }
    console.log('not found')
  }
  const increaseBalance = function (balance) {
    for (const bal of balances.value) {
      if (bal === balance) {
        bal.balance += 1000
        return
      }
    }
    console.log('not found')
  }
  return { balances, findBalance, increaseBalance }
})