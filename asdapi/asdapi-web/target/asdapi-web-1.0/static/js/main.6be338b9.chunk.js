(window.webpackJsonp=window.webpackJsonp||[]).push([[0],{112:function(e,t,n){"use strict";n.r(t);var a=n(1),l=n.n(a),c=n(29),r=n.n(c),o=(n(58),n(20)),i=n(21),u=n(23),s=n(22),m=n(24),h=n(114),E=(n(60),n(115)),d=n(113),v={maxWidth:400,margin:"0 auto 10px"},f=function(e){function t(){var e;return Object(o.a)(this,t),(e=Object(u.a)(this,Object(s.a)(t).call(this))).state={activos:[]},e}return Object(m.a)(t,e),Object(i.a)(t,[{key:"getActivos",value:function(){var e=this;fetch("http://localhost:8080/asd/services/servicioActivos/obtenerActivosFijos").then(function(e){return e.json()}).then(function(t){e.setState({activos:t.activosFijos})}).catch(function(e){console.log("Error Fetch Api :-S",e)})}},{key:"componentDidMount",value:function(){this.getActivos()}},{key:"render",value:function(){var e=this,t=l.a.createElement("tr",null);return this.state.activos.length>0&&(t=this.state.activos.map(function(e,t){return l.a.createElement("tr",{key:t},l.a.createElement("td",null,e.id),l.a.createElement("td",null,e.nombre),l.a.createElement("td",null,e.numeroInternoInventario),l.a.createElement("td",null,e.serial),l.a.createElement("td",null,e.tipoId.nombre),l.a.createElement("td",null,e.fechaBaja),l.a.createElement("td",null,e.fechaCompra))})),l.a.createElement("div",null,l.a.createElement("div",{className:"well",style:v},l.a.createElement(E.a,{bsStyle:"primary",bsSize:"large",block:!0,onClick:function(){e.getActivos()}},"Actualizar")),l.a.createElement(d.a,{hover:!0},l.a.createElement("thead",null,l.a.createElement("tr",null,l.a.createElement("th",null,"ID"),l.a.createElement("th",null,"NOMBRE"),l.a.createElement("th",null,"NUMERO INVENTARIO"),l.a.createElement("th",null,"SERIAL"),l.a.createElement("th",null,"TIPO"),l.a.createElement("th",null,"FECHA BAJA"),l.a.createElement("th",null,"FECHA COMPRA"))),l.a.createElement("tbody",null,t)))}}]),t}(a.Component),p=function(e){function t(){return Object(o.a)(this,t),Object(u.a)(this,Object(s.a)(t).apply(this,arguments))}return Object(m.a)(t,e),Object(i.a)(t,[{key:"render",value:function(){return l.a.createElement("div",{className:"App"},l.a.createElement("header",{className:"app-header"},l.a.createElement(h.a,{fluid:!0},l.a.createElement(h.a.Header,null,l.a.createElement(h.a.Brand,null,"Cliente Rest Prueba Tecnica")))),l.a.createElement("div",{className:"content"},l.a.createElement(f,null)))}}]),t}(a.Component);Boolean("localhost"===window.location.hostname||"[::1]"===window.location.hostname||window.location.hostname.match(/^127(?:\.(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)){3}$/));r.a.render(l.a.createElement(p,null),document.getElementById("root")),"serviceWorker"in navigator&&navigator.serviceWorker.ready.then(function(e){e.unregister()})},52:function(e,t,n){e.exports=n(112)},58:function(e,t,n){},60:function(e,t,n){}},[[52,2,1]]]);
//# sourceMappingURL=main.6be338b9.chunk.js.map