if(!self.define){let e,i={};const n=(n,s)=>(n=new URL(n+".js",s).href,i[n]||new Promise((i=>{if("document"in self){const e=document.createElement("script");e.src=n,e.onload=i,document.head.appendChild(e)}else e=n,importScripts(n),i()})).then((()=>{let e=i[n];if(!e)throw new Error(`Module ${n} didn’t register its module`);return e})));self.define=(s,a)=>{const c=e||("document"in self?document.currentScript.src:"")||location.href;if(i[c])return;let t={};const r=e=>n(e,c),o={module:{uri:c},exports:t,require:r};i[c]=Promise.all(s.map((e=>o[e]||r(e)))).then((e=>(a(...e),t)))}}define(["./workbox-f1770938"],(function(e){"use strict";importScripts(),self.skipWaiting(),e.clientsClaim(),e.precacheAndRoute([{url:"/_next/static/chunks/100-200533ff00cf36d4.js",revision:"wLlUiBo7tIG8FE5bJnmYR"},{url:"/_next/static/chunks/145-c52aaf32f731b1a7.js",revision:"wLlUiBo7tIG8FE5bJnmYR"},{url:"/_next/static/chunks/148-e05aecc9e4bf27c3.js",revision:"wLlUiBo7tIG8FE5bJnmYR"},{url:"/_next/static/chunks/164f4fb6-e9b72293e53f5e93.js",revision:"wLlUiBo7tIG8FE5bJnmYR"},{url:"/_next/static/chunks/411.da54227fe288b988.js",revision:"da54227fe288b988"},{url:"/_next/static/chunks/41ade5dc-e6840933a2201c74.js",revision:"wLlUiBo7tIG8FE5bJnmYR"},{url:"/_next/static/chunks/434-275cb75a9d694276.js",revision:"wLlUiBo7tIG8FE5bJnmYR"},{url:"/_next/static/chunks/448-87f58ef5ccb849e0.js",revision:"wLlUiBo7tIG8FE5bJnmYR"},{url:"/_next/static/chunks/464-661fe60ddd4d1a84.js",revision:"wLlUiBo7tIG8FE5bJnmYR"},{url:"/_next/static/chunks/531-02aa4d82806390e8.js",revision:"wLlUiBo7tIG8FE5bJnmYR"},{url:"/_next/static/chunks/602-c5e705e34a5f256c.js",revision:"wLlUiBo7tIG8FE5bJnmYR"},{url:"/_next/static/chunks/602dbae6-85a3083bfe6ff7e4.js",revision:"wLlUiBo7tIG8FE5bJnmYR"},{url:"/_next/static/chunks/626-10ea48988b3f1901.js",revision:"wLlUiBo7tIG8FE5bJnmYR"},{url:"/_next/static/chunks/763-46914bb7466af7cb.js",revision:"wLlUiBo7tIG8FE5bJnmYR"},{url:"/_next/static/chunks/969.79f025cb0c940914.js",revision:"79f025cb0c940914"},{url:"/_next/static/chunks/997-342b3a2884ddf31a.js",revision:"wLlUiBo7tIG8FE5bJnmYR"},{url:"/_next/static/chunks/a6eb9415-bced76ba6254581c.js",revision:"wLlUiBo7tIG8FE5bJnmYR"},{url:"/_next/static/chunks/ad2866b8.d16d5df33d15d005.js",revision:"d16d5df33d15d005"},{url:"/_next/static/chunks/app/(Calender)/month/page-631a6f41f94d9df2.js",revision:"wLlUiBo7tIG8FE5bJnmYR"},{url:"/_next/static/chunks/app/(Calender)/week/page-cadae268ffbb1409.js",revision:"wLlUiBo7tIG8FE5bJnmYR"},{url:"/_next/static/chunks/app/(User)/login/page-49fd65131364e26e.js",revision:"wLlUiBo7tIG8FE5bJnmYR"},{url:"/_next/static/chunks/app/(User)/signup/page-c58be2a3ef8372cb.js",revision:"wLlUiBo7tIG8FE5bJnmYR"},{url:"/_next/static/chunks/app/_not-found/page-297b34e7c3eb66a3.js",revision:"wLlUiBo7tIG8FE5bJnmYR"},{url:"/_next/static/chunks/app/layout-8467074fab314978.js",revision:"wLlUiBo7tIG8FE5bJnmYR"},{url:"/_next/static/chunks/app/not-found-37401889a00baea4.js",revision:"wLlUiBo7tIG8FE5bJnmYR"},{url:"/_next/static/chunks/app/page-59ceaf2c72797739.js",revision:"wLlUiBo7tIG8FE5bJnmYR"},{url:"/_next/static/chunks/app/project/%5BprojectId%5D/(calender)/layout-1017cdddcf750835.js",revision:"wLlUiBo7tIG8FE5bJnmYR"},{url:"/_next/static/chunks/app/project/%5BprojectId%5D/(calender)/month/page-49e44159b383f0fe.js",revision:"wLlUiBo7tIG8FE5bJnmYR"},{url:"/_next/static/chunks/app/project/%5BprojectId%5D/(calender)/week/page-835c54b9223c2c8c.js",revision:"wLlUiBo7tIG8FE5bJnmYR"},{url:"/_next/static/chunks/app/project/%5BprojectId%5D/layout-727fe6ccffe8e7f9.js",revision:"wLlUiBo7tIG8FE5bJnmYR"},{url:"/_next/static/chunks/app/project/%5BprojectId%5D/page-fc7fa206e2604e97.js",revision:"wLlUiBo7tIG8FE5bJnmYR"},{url:"/_next/static/chunks/app/project/%5BprojectId%5D/sprint/page-c29f971dc9881656.js",revision:"wLlUiBo7tIG8FE5bJnmYR"},{url:"/_next/static/chunks/app/project/%5BprojectId%5D/summary/page-8ec666eb11dbc8ed.js",revision:"wLlUiBo7tIG8FE5bJnmYR"},{url:"/_next/static/chunks/app/project/%5BprojectId%5D/worklog/page-a7ab3a7c7cb9d8c0.js",revision:"wLlUiBo7tIG8FE5bJnmYR"},{url:"/_next/static/chunks/app/project/page-ba2d033fc4365039.js",revision:"wLlUiBo7tIG8FE5bJnmYR"},{url:"/_next/static/chunks/bc98253f.76245f824ceb8b38.js",revision:"76245f824ceb8b38"},{url:"/_next/static/chunks/dc112a36-4c04683df8e41f5f.js",revision:"wLlUiBo7tIG8FE5bJnmYR"},{url:"/_next/static/chunks/fd9d1056-2e3879422354090c.js",revision:"wLlUiBo7tIG8FE5bJnmYR"},{url:"/_next/static/chunks/framework-00a8ba1a63cfdc9e.js",revision:"wLlUiBo7tIG8FE5bJnmYR"},{url:"/_next/static/chunks/main-0e8b00ae3cd8f0a0.js",revision:"wLlUiBo7tIG8FE5bJnmYR"},{url:"/_next/static/chunks/main-app-a578d9972a29734f.js",revision:"wLlUiBo7tIG8FE5bJnmYR"},{url:"/_next/static/chunks/pages/_app-6f5312077c4a5604.js",revision:"wLlUiBo7tIG8FE5bJnmYR"},{url:"/_next/static/chunks/pages/_error-2f37a8b74d52c0a0.js",revision:"wLlUiBo7tIG8FE5bJnmYR"},{url:"/_next/static/chunks/polyfills-42372ed130431b0a.js",revision:"846118c33b2c0e922d7b3a7676f81f6f"},{url:"/_next/static/chunks/webpack-4acc9c8f5536f03b.js",revision:"wLlUiBo7tIG8FE5bJnmYR"},{url:"/_next/static/css/6164f24518197aa5.css",revision:"6164f24518197aa5"},{url:"/_next/static/css/e085d032bb7c651c.css",revision:"e085d032bb7c651c"},{url:"/_next/static/media/ff840cfebfb63b0c-s.p.woff2",revision:"302ec55f5b4320354ec6b35a53dead87"},{url:"/_next/static/wLlUiBo7tIG8FE5bJnmYR/_buildManifest.js",revision:"85c22b791f4196634afc8bd83d26f197"},{url:"/_next/static/wLlUiBo7tIG8FE5bJnmYR/_ssgManifest.js",revision:"b6652df95db52feb4daf4eca35380933"},{url:"/icons/icon-120x120.png",revision:"315c926e38a6bdce4ddc8bbddff6c894"},{url:"/icons/icon-152x152.png",revision:"09e54a8391282637314ad8d7b9c05e98"},{url:"/icons/icon-310x310.png",revision:"dccc94541e74fb27c0111125110ba8c2"},{url:"/icons/icon-60x60.png",revision:"37d1d913d0f6693b098721a097ed4e6a"},{url:"/img/High.png",revision:"1874680fe5675df4f65161630ec86251"},{url:"/img/Highest.png",revision:"52cb0756e02a09bb5031ed0b3e910d82"},{url:"/img/Low.png",revision:"343828240cc8d9aaa3ed806fe849907d"},{url:"/img/Lowest.png",revision:"5eff8c577b6268ee13859f624fd2d4c9"},{url:"/img/Medium.png",revision:"87d74507d1b8a3c608288b99ba84a063"},{url:"/img/avatar.png",revision:"73b71c7baff24bbfde11a968a927240a"},{url:"/img/calendarbtn.png",revision:"08b13fce69c2a2983b4832b2f4c260dc"},{url:"/img/camera.png",revision:"ca91d915da43a3130ae7cb95ece46ff2"},{url:"/img/chatbot.png",revision:"a8ee7dc852a7d33c584ff8632018dfb5"},{url:"/img/chaticon.png",revision:"ac5c0e524a21f4b5a9368c1c78bce69b"},{url:"/img/chaticongreen.png",revision:"27c12f9531e6fc92d5e68c6bb2f8325a"},{url:"/img/chatsend.png",revision:"e60e3bc8ac03f033c9321dd4b5d3d3c6"},{url:"/img/chatsendgreen.png",revision:"9bc9b2e889b11e2f73870d8d5c3a67c9"},{url:"/img/closebtn.png",revision:"84ee2534e9c1132d837a6621e86175e6"},{url:"/img/information.png",revision:"40a431487dcaec090953f27da7f5eb83"},{url:"/img/inputenter.png",revision:"a0eb8fa0c75f166528bc7c11be760c9b"},{url:"/img/leftarrow.png",revision:"395c4148d1f1b240b283292e6872b620"},{url:"/img/makesprint.png",revision:"0d8d2a202401b903f9c18ed183efa632"},{url:"/img/notification-icon.gif",revision:"efba5adadb9aac22c23f1dcda7aff622"},{url:"/img/pencil.png",revision:"75d477c877abf1c00a11931a7ae9fb73"},{url:"/img/plusbtn.png",revision:"fdeeec487488e4098895407473b2a31b"},{url:"/img/plusstackbtn.png",revision:"a144f7574891a1c97f3933f6b9466d96"},{url:"/img/profile.png",revision:"37e8aa0a7e6c588b30966418d6e7a0bd"},{url:"/img/rightarrow.png",revision:"ee5101b0f8843826573ccb4b9c3d7430"},{url:"/img/sad_ai.png",revision:"4d650222342ebd4aadf2c6edf8252c6a"},{url:"/img/signleftarrow.png",revision:"27a211a8659bfc4b82e24d6387f9d41a"},{url:"/img/signrightarrow.png",revision:"4212cdf343ade94193e2593161959778"},{url:"/img/step1.png",revision:"0acaefa5a7fe32bc947d70b26b2b70ca"},{url:"/img/step2.png",revision:"69db3d23e344e8c6dad0191290adc494"},{url:"/img/step3.png",revision:"17c65ef8ab8d6a9aa15748868ef36200"},{url:"/img/step4.png",revision:"568f98b73ded0901130dafca4c40de03"},{url:"/img/step5.png",revision:"6464410a6836a00cc78650340dbffddb"},{url:"/img/step6.png",revision:"66b9c3dd756cf73a36e370fb2c07a3e4"},{url:"/img/step7.png",revision:"f43c024ed9709a1ade4e7dd9bfb785ae"},{url:"/img/step8.png",revision:"9f827fd8d37eab9dee789a2193a68936"},{url:"/img/step9.png",revision:"0c1b2d054a69477bde078d60f32a24a8"},{url:"/img/teamprofile.png",revision:"1f11e636f7b9cbfd64bd5178e139cb51"},{url:"/img/weekleftarrow.png",revision:"77c510459427e2e3186d79477b85e7be"},{url:"/img/weekrightarrow.png",revision:"266429bf89233e4315c70cb00d450c9a"},{url:"/lottie/Animation - 1730162499899.json",revision:"0909400f8ea5dac7f901dbfd72b84f00"},{url:"/lottie/Animation - 1730424329200.json",revision:"3e13d18ae2b1aea4497fde26642c3aa2"},{url:"/lottie/Animation - 1730873717286.json",revision:"1ddb574e13335f05b7873d6fc66ea01e"},{url:"/lottie/Animation - 1731310411267.json",revision:"ca374784b160bd0475d442d0df3c3596"},{url:"/lottie/Animation - 1731410863192.json",revision:"765cf874eae6bedb9f4b0bac06aa555f"},{url:"/lottie/Animation - 1731658876737.json",revision:"c1019837d0bfa510870180c983890b9a"},{url:"/lottie/Animation - 1731821799004.json",revision:"4e2609bdafb27261e2bf2c767e22f8fc"},{url:"/manifest.json",revision:"304163163f64765745b6f67296028e9c"},{url:"/svg/loading.svg",revision:"9ace0be98b0e5b224ecbcfb7e55db409"}],{ignoreURLParametersMatching:[/^utm_/,/^fbclid$/]}),e.cleanupOutdatedCaches(),e.registerRoute("/",new e.NetworkFirst({cacheName:"start-url",plugins:[{cacheWillUpdate:async({response:e})=>e&&"opaqueredirect"===e.type?new Response(e.body,{status:200,statusText:"OK",headers:e.headers}):e}]}),"GET"),e.registerRoute(/^https:\/\/fonts\.(?:gstatic)\.com\/.*/i,new e.CacheFirst({cacheName:"google-fonts-webfonts",plugins:[new e.ExpirationPlugin({maxEntries:4,maxAgeSeconds:31536e3})]}),"GET"),e.registerRoute(/^https:\/\/fonts\.(?:googleapis)\.com\/.*/i,new e.StaleWhileRevalidate({cacheName:"google-fonts-stylesheets",plugins:[new e.ExpirationPlugin({maxEntries:4,maxAgeSeconds:604800})]}),"GET"),e.registerRoute(/\.(?:eot|otf|ttc|ttf|woff|woff2|font.css)$/i,new e.StaleWhileRevalidate({cacheName:"static-font-assets",plugins:[new e.ExpirationPlugin({maxEntries:4,maxAgeSeconds:604800})]}),"GET"),e.registerRoute(/\.(?:jpg|jpeg|gif|png|svg|ico|webp)$/i,new e.StaleWhileRevalidate({cacheName:"static-image-assets",plugins:[new e.ExpirationPlugin({maxEntries:64,maxAgeSeconds:2592e3})]}),"GET"),e.registerRoute(/\/_next\/static.+\.js$/i,new e.CacheFirst({cacheName:"next-static-js-assets",plugins:[new e.ExpirationPlugin({maxEntries:64,maxAgeSeconds:86400})]}),"GET"),e.registerRoute(/\/_next\/image\?url=.+$/i,new e.StaleWhileRevalidate({cacheName:"next-image",plugins:[new e.ExpirationPlugin({maxEntries:64,maxAgeSeconds:86400})]}),"GET"),e.registerRoute(/\.(?:mp3|wav|ogg)$/i,new e.CacheFirst({cacheName:"static-audio-assets",plugins:[new e.RangeRequestsPlugin,new e.ExpirationPlugin({maxEntries:32,maxAgeSeconds:86400})]}),"GET"),e.registerRoute(/\.(?:mp4|webm)$/i,new e.CacheFirst({cacheName:"static-video-assets",plugins:[new e.RangeRequestsPlugin,new e.ExpirationPlugin({maxEntries:32,maxAgeSeconds:86400})]}),"GET"),e.registerRoute(/\.(?:js)$/i,new e.StaleWhileRevalidate({cacheName:"static-js-assets",plugins:[new e.ExpirationPlugin({maxEntries:48,maxAgeSeconds:86400})]}),"GET"),e.registerRoute(/\.(?:css|less)$/i,new e.StaleWhileRevalidate({cacheName:"static-style-assets",plugins:[new e.ExpirationPlugin({maxEntries:32,maxAgeSeconds:86400})]}),"GET"),e.registerRoute(/\/_next\/data\/.+\/.+\.json$/i,new e.StaleWhileRevalidate({cacheName:"next-data",plugins:[new e.ExpirationPlugin({maxEntries:32,maxAgeSeconds:86400})]}),"GET"),e.registerRoute(/\.(?:json|xml|csv)$/i,new e.NetworkFirst({cacheName:"static-data-assets",plugins:[new e.ExpirationPlugin({maxEntries:32,maxAgeSeconds:86400})]}),"GET"),e.registerRoute((({sameOrigin:e,url:{pathname:i}})=>!(!e||i.startsWith("/api/auth/callback")||!i.startsWith("/api/"))),new e.NetworkFirst({cacheName:"apis",networkTimeoutSeconds:10,plugins:[new e.ExpirationPlugin({maxEntries:16,maxAgeSeconds:86400})]}),"GET"),e.registerRoute((({request:e,url:{pathname:i},sameOrigin:n})=>"1"===e.headers.get("RSC")&&"1"===e.headers.get("Next-Router-Prefetch")&&n&&!i.startsWith("/api/")),new e.NetworkFirst({cacheName:"pages-rsc-prefetch",plugins:[new e.ExpirationPlugin({maxEntries:32,maxAgeSeconds:86400})]}),"GET"),e.registerRoute((({request:e,url:{pathname:i},sameOrigin:n})=>"1"===e.headers.get("RSC")&&n&&!i.startsWith("/api/")),new e.NetworkFirst({cacheName:"pages-rsc",plugins:[new e.ExpirationPlugin({maxEntries:32,maxAgeSeconds:86400})]}),"GET"),e.registerRoute((({url:{pathname:e},sameOrigin:i})=>i&&!e.startsWith("/api/")),new e.NetworkFirst({cacheName:"pages",plugins:[new e.ExpirationPlugin({maxEntries:32,maxAgeSeconds:86400})]}),"GET"),e.registerRoute((({sameOrigin:e})=>!e),new e.NetworkFirst({cacheName:"cross-origin",networkTimeoutSeconds:10,plugins:[new e.ExpirationPlugin({maxEntries:32,maxAgeSeconds:3600})]}),"GET")}));