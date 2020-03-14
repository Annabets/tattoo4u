(this.webpackJsonptattoo4u=this.webpackJsonptattoo4u||[]).push([[0],{59:function(e,t,a){e.exports=a.p+"static/media/tattoo.c4a292e7.jpg"},63:function(e,t,a){e.exports=a(77)},69:function(e,t,a){},77:function(e,t,a){"use strict";a.r(t);var n=a(0),o=a.n(n),r=a(13),l=a.n(r);Boolean("localhost"===window.location.hostname||"[::1]"===window.location.hostname||window.location.hostname.match(/^127(?:\.(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)){3}$/));a(68),a(69);var i=a(20),s=a(48),c=a(49),u=a(9),d={userData:{},isSigningIn:!1,error:""};var m="SET_SCROLL_FLAG",h="GET_PHOTOS_REQUEST",p="GET_PHOTOS_SUCCESS",g="GET_PHOTOS_FAILURE",f={tattooPhotos:[],isLoadingPhotos:!1,isUploadFailed:!1};var E="SET_COLUMNS",P="SET_MODAL_PHOTO",v="SET_MODAL_OPEN_FLAG",O="TOGGLE_MODAL_PHOTO_LIKE",S={columns:[],modalPhoto:{},isModalOpen:!1};var b=Object(i.c)({signIn:function(){var e=arguments.length>0&&void 0!==arguments[0]?arguments[0]:d,t=arguments.length>1?arguments[1]:void 0;switch(t.type){case"SIGN_IN_USER_REQUEST":return Object(u.a)({},e,{isSigningIn:!0});case"SIGN_IN_USER_SUCCESS":return Object(u.a)({},e,{isSigningIn:!1,userData:t.userData});case"SIGN_IN_USER_FAILURE":return{isSigningIn:!1,error:t.payload};default:return e}},homePage:function(){var e=arguments.length>0&&void 0!==arguments[0]?arguments[0]:f,t=arguments.length>1?arguments[1]:void 0;switch(t.type){case m:return Object(u.a)({},e,{isOnTop:t.payload});case h:return Object(u.a)({},e,{isLoadingPhotos:!0});case p:return Object(u.a)({},e,{tattooPhotos:e.tattooPhotos.concat(t.payload),isLoadingPhotos:!1});case g:return Object(u.a)({},e,{isLoadingPhotos:!1,isUploadFailed:!0});default:return e}},photoGrid:function(){var e=arguments.length>0&&void 0!==arguments[0]?arguments[0]:S,t=arguments.length>1?arguments[1]:void 0;switch(t.type){case E:return Object(u.a)({},e,{columns:t.payload});case v:return Object(u.a)({},e,{isModalOpen:t.payload});case P:return Object(u.a)({},e,{modalPhoto:t.payload});case O:return Object(u.a)({},e,{modalPhoto:Object(u.a)({},e.modalPhoto,{liked:!e.modalPhoto.liked})});default:return e}}}),y=Object(i.d)(b,Object(i.a)(s.a,Object(c.createLogger)())),M=a(60),N=a(17),C=a(15),L=a(81),j=a(82),I=a(79),k=function(e){var t=e.user;return o.a.createElement(L.a,{bg:"primary",variant:"dark"},o.a.createElement(L.a.Brand,{href:"/"},"TATTOO4U"),o.a.createElement(j.a,{className:"mr-auto"},o.a.createElement(j.a.Link,{href:"/"},"Gallery"),o.a.createElement(j.a.Link,{href:"/"},"Studios"),o.a.createElement(j.a.Link,{href:"/"},"Masters")),o.a.createElement(j.a,null,t?o.a.createElement(I.a,{title:t.userName,id:"profile-nav-dropdown"},o.a.createElement(I.a.Item,{href:"/"},"My profile"),o.a.createElement(I.a.Divider,null),o.a.createElement(I.a.Item,null,"Sign Out")):o.a.createElement(j.a.Link,{href:"/signIn"},"SIGN IN")))},_=function(e){return e.text().then((function(t){return e.ok?Promise.resolve(JSON.parse(t)):Promise.reject(e.statusText)})).catch((function(e){return Promise.reject(e.message)}))},w=function(){return fetch("http://localhost:8080/photos",{method:"GET"}).then(_)},F=function(e){return fetch("http://localhost:8080/signIn",{method:"POST",body:e}).then(_)};var G={setScrollFlag:function(e){return{type:m,payload:e}},getTattooPhotos:function(){return function(e,t){e({type:h}),w(t().homePage.tattooPhotos.length+1).then((function(t){e({type:p,payload:t})}),(function(t){e({type:g,payload:t})}))}}},T=a(24),U=a(25),R=a(27),x=a(26),D=a(28),A=function(e){function t(e){var a;return Object(T.a)(this,t),(a=Object(R.a)(this,Object(x.a)(t).call(this,e))).handleCloseModal=function(e){"Modal"!==e.target.className&&"close-btn"!==e.target.className||(a.props.setModalOpenFlag(!1),document.body.style="overflow-y: visible")},a}return Object(D.a)(t,e),Object(U.a)(t,[{key:"render",value:function(){var e=this.props.modalPhoto;return o.a.createElement(o.a.Fragment,null,o.a.createElement("div",{className:"Modal",onClick:this.handleCloseModal},o.a.createElement("span",{className:"close-btn"},"\xd7"),o.a.createElement("div",{className:"Modal-content"},o.a.createElement("div",{className:"Modal-action-bar"}),o.a.createElement("div",{className:"Modal-photo"},o.a.createElement("img",{src:"http://localhost:8080/img/".concat(e.filename),alt:""})))))}}]),t}(o.a.Component),H=function(e){function t(e){var a;return Object(T.a)(this,t),(a=Object(R.a)(this,Object(x.a)(t).call(this,e))).handleGetMorePhotos=function(){var e=a.props,t=e.isSearchPage,n=e.searchQuery,o=e.getMorePhotos;t?o(n):o()},a.renderColumn=function(e,t){return a.props.photos.filter((function(a,n){return n%t===e})).map((function(e){return console.log(e),o.a.createElement("div",{className:"Column-item",key:e.id,id:e.id,onClick:a.handleGridItemClick},o.a.createElement("img",{className:"Column-item-img",src:"http://localhost:8080/img/".concat(e.filename),alt:""}))}))},a.renderContainerColumns=function(e){return e.map((function(t,n){return o.a.createElement("div",{key:n,className:"Grid-column"},a.renderColumn(n,e.length))}))},a.handleGridItemClick=function(e){var t=a.props,n=t.setModalOpenFlag,o=t.setModalPhoto,r=t.photos;"Column-item-content"!==e.target.className&&"Column-item-img"!==e.target.className||(o(r.find((function(t){return t.id===Number(e.currentTarget.id)}))),n(!0),document.body.style="overflow-y: hidden")},a.handleModalLikeBtnClick=function(){var e=a.props,t=e.modalPhoto,n=e.likePhoto;(0,e.likeModalPhoto)(),n(t.id)},a.handleResize=function(){var e=a.props,t=e.columns,n=e.setColumns,o=t.length,r=window.innerWidth;r<625&&1!==o?n(1):r>625&&r<1e3&&2!==o?n(2):r>1e3&&r<1500&&3!==o?n(3):r>1500&&4!==o&&n(4)},a.handleScroll=function(){var e=a.props,t=e.isLoadingPhotos,n=e.isUploadFailed;2*window.innerHeight+window.scrollY>=document.body.scrollHeight&&!t&&!n&&a.handleGetMorePhotos()},a.componentDidMount=function(){window.addEventListener("resize",a.handleResize),window.addEventListener("scroll",a.handleScroll)},a.componentWillUnmount=function(){window.removeEventListener("resize",a.handleResize),window.removeEventListener("scroll",a.handleScroll)},a.handleGetMorePhotos(),a.handleResize(),a}return Object(D.a)(t,e),Object(U.a)(t,[{key:"render",value:function(){var e=this.props,t=e.columns,a=e.isHomePage,n=e.isSearchPage,r=e.photos,l=e.isLoadingPhotos,i=e.isUploadFailed,s=e.isModalOpen,c=e.modalPhoto,u=e.setModalOpenFlag,d=e.searchQuery;return o.a.createElement(o.a.Fragment,null,o.a.createElement("section",{className:"Photo-grid",style:{top:n?"57px":null}},o.a.createElement("div",{className:"Photo-grid-title"},a&&o.a.createElement("h2",null,"Free Stock Photos"),n&&o.a.createElement("h1",null,"".concat(d," Photos"))),r.length>0&&o.a.createElement("div",{className:"Grid-container"},this.renderContainerColumns(t)),l&&o.a.createElement("div",{className:"Loading-indicator"},"Loading..."),i&&o.a.createElement("h3",null,"Failed to upload photos")),s&&o.a.createElement(A,{modalPhoto:c,setModalOpenFlag:u,handleLikeBtnClick:this.handleModalLikeBtnClick}))}}]),t}(o.a.Component),z=function(e){for(var t=0,a=[];t<e;)a[t]=t++;return a};var B={setColumns:function(e){return{type:E,payload:z(e)}},setModalOpenFlag:function(e){return{type:v,payload:e}},setModalPhoto:function(e){return{type:P,payload:e}},toggleModalPhotoLike:function(){return{type:O}}};var Q=Object(C.b)((function(e){return{photoGrid:e.photoGrid}}),(function(e){return{setColumns:function(t){return e(B.setColumns(t))},setModalOpenFlag:function(t){return e(B.setModalOpenFlag(t))},setModalPhoto:function(t){return e(B.setModalPhoto(t))},likeModalPhoto:function(){return e(B.toggleModalPhotoLike())}}}))((function(e){var t=e.pages,a=e.photoGrid,n=e.setColumns,r=e.setModalOpenFlag,l=e.setModalPhoto,i=e.getMorePhotos,s=e.isLoadingPhotos,c=e.isUploadFailed;return o.a.createElement(o.a.Fragment,null,o.a.createElement(H,{photos:t,getMorePhotos:i,columns:a.columns,isLoadingPhotos:s,isUploadFailed:c,modalPhoto:a.modalPhoto,isModalOpen:a.isModalOpen,setColumns:n,setModalOpenFlag:r,setModalPhoto:l}))}));var W=Object(C.b)((function(e){return{homePage:e.homePage}}),(function(e){return{getPhotos:function(){return e(G.getTattooPhotos())}}}))((function(e){var t=e.homePage,a=e.getPhotos;return o.a.createElement(o.a.Fragment,null,o.a.createElement(k,null),o.a.createElement(Q,{isHomePage:!0,isSearchPage:!1,pages:t.tattooPhotos,getMorePhotos:a,isLoadingPhotos:t.isLoadingPhotos,isUploadFailed:t.isUploadFailed}))})),J=a(80),K=a(57);var Y=a(59),$=a.n(Y),q=function(e){function t(e){var a;return Object(T.a)(this,t),(a=Object(R.a)(this,Object(x.a)(t).call(this,e))).handleSubmit=function(e){e.preventDefault(),a.props.signIn(a.state)},a.state={userName:"",password:""},a}return Object(D.a)(t,e),Object(U.a)(t,[{key:"render",value:function(){var e=this,t=this.state,a=t.userName,n=t.password;return o.a.createElement("div",{className:"sign-in",style:{backgroundImage:"url(".concat($.a,")")}},o.a.createElement("div",{className:"form-container"},o.a.createElement("div",{className:"form-content"},o.a.createElement("h2",{className:"form-title"},"Sign In"),o.a.createElement(J.a,{onSubmit:this.handleSubmit},o.a.createElement(J.a.Group,null,o.a.createElement(J.a.Control,{type:"text",placeholder:"Username",value:a,onChange:function(t){return e.setState({userName:t.target.value})}})),o.a.createElement(J.a.Group,null,o.a.createElement(J.a.Control,{type:"password",placeholder:"Password",value:n,onChange:function(t){return e.setState({password:t.target.value})}})),o.a.createElement(K.a,{variant:"primary",type:"submit",block:!0,className:"form-btn"},"Sign In"),o.a.createElement(J.a.Text,{className:"text-secondary mt-2"},"Or ",o.a.createElement("a",{href:"#"},"register now!"))))))}}]),t}(o.a.Component),V=Object(C.b)(null,(function(e){return{signIn:function(t){return e(function(e){return function(t){t({type:"SIGN_IN_USER_REQUEST"}),F(e).then((function(e){t({type:"SIGN_IN_USER_SUCCESS",userData:e})}),(function(e){t({type:"SIGN_IN_USER_FAILURE",payload:e})}))}}(t))}}}))(q);l.a.render(o.a.createElement((function(){return o.a.createElement(C.a,{store:y},o.a.createElement(M.a,null,o.a.createElement(N.a,{exact:!0,path:"/"},o.a.createElement(W,null)),o.a.createElement(N.a,{exact:!0,path:"/signIn"},o.a.createElement(V,null))))}),null),document.getElementById("app")),"serviceWorker"in navigator&&navigator.serviceWorker.ready.then((function(e){e.unregister()})).catch((function(e){console.error(e.message)}))}},[[63,1,2]]]);
//# sourceMappingURL=main.41e5f77b.chunk.js.map