<template>
  <div class="main-footer">
    <div class="contents">
      Copyright © SAMSUNG All Rights Reserved.
    </div>
  </div>
</template>

<style>
.main-footer {
  width: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 0 10px;
}

@media (max-width: 700px) {
  .main-footer {
    height: 190px;
  }
  .main-footer .contents {
    line-height: 190px;
    text-align: center;
    margin: 0 auto;
  }
}

@media (min-width: 701px) {
  .main-footer {
    height: 110px;
  }
  .main-footer .contents {
    line-height: 110px;
    text-align: center;
    margin: 0 auto;
  }
}
</style>

<script>
import { reactive } from 'vue'
import { useStore } from 'vuex'

export default {
  name: 'MainFooter',

  props: {
    height: {
      type: String,
      default: '110px'
    }
  },

  setup() {
    const state = reactive({})

    return { state }
  }
}
</script>
