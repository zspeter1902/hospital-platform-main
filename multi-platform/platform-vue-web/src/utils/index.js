
/**
 * 是否有权限
 * @param {*} key
 */
export function isAuth (key){
    let permissionList = JSON.parse(localStorage.getItem('permissionList'));
    ///console.log('所有的权限:'+JSON.stringify(permissionList));
    let permission = permissionList.find(item => item.permissionCode == key)
    let isNull = this.$isNull(permission)
    //console.log(this.$isNull(permission))
    return !isNull
}

export function parseTime(time, cFormat) {
    if (arguments.length === 0 || !time) {
        return null;
    }
    const format = cFormat || '{y}-{m}-{d} {h}:{i}:{s}';
    let date;
    if (typeof time == 'object') {
        date = time;
    } else {
        if (('' + time).length === 10) time = parseInt(time) * 1000;
        date = new Date(time);
    }
    const formatObj = {
        y: date.getFullYear(),
        m: date.getMonth() + 1,
        d: date.getDate(),
        h: date.getHours(),
        i: date.getMinutes(),
        s: date.getSeconds(),
        a: date.getDay()
    };
    const time_str = format.replace(/{(y|m|d|h|i|s|a)+}/g, (result, key) => {
        let value = formatObj[key];
        if (key === 'a') return ['一', '二', '三', '四', '五', '六', '日'][value - 1];
        if (result.length > 0 && value < 10) {
            value = '0' + value;
        }
        return value || 0;
    });
    return time_str;
}

export function formatTime(time, option) {
    time = +time * 1000;
    const d = new Date(time);
    const now = Date.now();

    const diff = (now - d) / 1000;

    if (diff < 30) {
        return '刚刚'
    } else if (diff < 3600) { // less 1 hour
        return Math.ceil(diff / 60) + '分钟前'
    } else if (diff < 3600 * 24) {
        return Math.ceil(diff / 3600) + '小时前'
    } else if (diff < 3600 * 24 * 2) {
        return '1天前'
    }
    if (option) {
        return parseTime(time, option)
    } else {
        return d.getMonth() + 1 + '月' + d.getDate() + '日' + d.getHours() + '时' + d.getMinutes() + '分'
    }
}

// 格式化时间
export function getQueryObject(url) {
    url = url == null ? window.location.href : url;
    const search = url.substring(url.lastIndexOf('?') + 1);
    const obj = {};
    const reg = /([^?&=]+)=([^?&=]*)/g;
    search.replace(reg, (rs, $1, $2) => {
        const name = decodeURIComponent($1);
        let val = decodeURIComponent($2);
        val = String(val);
        obj[name] = val;
        return rs;
    });
    return obj;
}


/**
 *get getByteLen
 * @param {Sting} val input value
 * @returns {number} output value
 */
export function getByteLen(val) {
    let len = 0;
    for (let i = 0; i < val.length; i++) {
        if (val[i].match(/[^\x00-\xff]/ig) != null) {
            len += 1;
        } else {
            len += 0.5;
        }
    }
    return Math.floor(len);
}

export function cleanArray(actual) {
    const newArray = [];
    for (let i = 0; i < actual.length; i++) {
        if (actual[i]) {
            newArray.push(actual[i]);
        }
    }
    return newArray;
}

export function param(json) {
    if (!json) return '';
    return cleanArray(Object.keys(json).map(key => {
        if (json[key] === undefined) return '';
        return encodeURIComponent(key) + '=' +
            encodeURIComponent(json[key]);
    })).join('&');
}

export function param2Obj(url) {
    const search = url.split('?')[1];
    if (!search) {
        return {}
    }
    return JSON.parse('{"' + decodeURIComponent(search).replace(/"/g, '\\"').replace(/&/g, '","').replace(/=/g, '":"') + '"}');
}

export function html2Text(val) {
    const div = document.createElement('div');
    div.innerHTML = val;
    return div.textContent || div.innerText;
}

export function objectMerge(target, source) {
    /* Merges two  objects,
     giving the last one precedence */

    if (typeof target !== 'object') {
        target = {};
    }
    if (Array.isArray(source)) {
        return source.slice();
    }
    for (const property in source) {
        if (source.hasOwnProperty(property)) {
            const sourceProperty = source[property];
            if (typeof sourceProperty === 'object') {
                target[property] = objectMerge(target[property], sourceProperty);
                continue;
            }
            target[property] = sourceProperty;
        }
    }
    return target;
}


export function scrollTo(element, to, duration) {
    if (duration <= 0) return;
    const difference = to - element.scrollTop;
    const perTick = difference / duration * 10;
    setTimeout(() => {
        console.log(new Date())
        element.scrollTop = element.scrollTop + perTick;
        if (element.scrollTop === to) return;
        scrollTo(element, to, duration - 10);
    }, 10);
}

export function toggleClass(element, className) {
    if (!element || !className) {
        return;
    }
    let classString = element.className;
    const nameIndex = classString.indexOf(className);
    if (nameIndex === -1) {
        classString += '' + className;
    } else {
        classString = classString.substr(0, nameIndex) + classString.substr(nameIndex + className.length);
    }
    element.className = classString;
}

export const pickerOptions = [
    {
        text: '今天',
        onClick(picker) {
            const end = new Date();
            const start = new Date(new Date().toDateString());
            end.setTime(start.getTime());
            picker.$emit('pick', [start, end]);
        }
    }, {
        text: '最近一周',
        onClick(picker) {
            const end = new Date(new Date().toDateString());
            const start = new Date();
            start.setTime(end.getTime() - 3600 * 1000 * 24 * 7);
            picker.$emit('pick', [start, end]);
        }
    }, {
        text: '最近一个月',
        onClick(picker) {
            const end = new Date(new Date().toDateString());
            const start = new Date();
            start.setTime(start.getTime() - 3600 * 1000 * 24 * 30);
            picker.$emit('pick', [start, end]);
        }
    }, {
        text: '最近三个月',
        onClick(picker) {
            const end = new Date(new Date().toDateString());
            const start = new Date();
            start.setTime(start.getTime() - 3600 * 1000 * 24 * 90);
            picker.$emit('pick', [start, end]);
        }
    }]

export function getTime(type) {
    if (type === 'start') {
        return new Date().getTime() - 3600 * 1000 * 24 * 90
    } else {
        return new Date(new Date().toDateString())
    }
}

export function debounce(func, wait, immediate) {
    let timeout, args, context, timestamp, result;

    const later = function () {
        // 据上一次触发时间间隔
        const last = +new Date() - timestamp;

        // 上次被包装函数被调用时间间隔last小于设定时间间隔wait
        if (last < wait && last > 0) {
            timeout = setTimeout(later, wait - last);
        } else {
            timeout = null;
            // 如果设定为immediate===true，因为开始边界已经调用过了此处无需调用
            if (!immediate) {
                result = func.apply(context, args);
                if (!timeout) context = args = null;
            }
        }
    };

    return function (...args) {
        context = this;
        timestamp = +new Date();
        const callNow = immediate && !timeout;
        // 如果延时不存在，重新设定延时
        if (!timeout) timeout = setTimeout(later, wait);
        if (callNow) {
            result = func.apply(context, args);
            context = args = null;
        }

        return result;
    };
}
export function formatDate(datetime) {
    const year = datetime.getFullYear();
    const month = (datetime.getMonth() + 1 < 10) ? '0' + (datetime.getMonth() + 1) : datetime.getMonth() + 1;
    const day = datetime.getDate() < 10 ? '0' + datetime.getDate() : datetime.getDate();
    return year + '/' + month + '/' + day;
}

export function formatDateYYYYMMDD(datetime) {
    const year = datetime.getFullYear();
    const month = (datetime.getMonth() + 1 < 10) ? '0' + (datetime.getMonth() + 1) : datetime.getMonth() + 1;
    const day = datetime.getDate() < 10 ? '0' + datetime.getDate() : datetime.getDate();
    return year + '-' + month + '-' + day;
}

export function formatDateYYYYMMDDHHMMSS(datetime) {
    const year = datetime.getFullYear();
    const month = (datetime.getMonth() + 1 < 10) ? '0' + (datetime.getMonth() + 1) : datetime.getMonth() + 1;
    const day = datetime.getDate() < 10 ? '0' + datetime.getDate() : datetime.getDate();
    const h = datetime.getHours() < 10 ? '0' + datetime.getHours() : datetime.getHours();
    const m = datetime.getMinutes() < 10 ? '0' + datetime.getMinutes() : datetime.getMinutes();
    const s = datetime.getSeconds() < 10 ? '0' + datetime.getSeconds() : datetime.getSeconds();
    return year + '-' + month + '-' + day + ' ' + h + ':' + m + ':' + s;
}

export function checkImgType(file) {
    let testmsg = file.name.substring(file.name.lastIndexOf('.') + 1)
    const imgType = ['jpg', 'bmp', 'png', 'gif', 'jpeg']
    const data = { message: '', check: true, }
    if (imgType.indexOf(testmsg) < 0) {  //根据后缀，判断是否符合图片格式
        data.message = '暂不支持此图片格式'
        data.check = false
        return data
    }
    const isLt2M = file.size / 1024 / 1024 < 10
    if (!isLt2M) {
        data.message = '上传文件大小不能超过 10MB'
        data.check = false
        return data
    }
    return data
}

export function checkExcelType(file) {
    let testmsg = file.name.substring(file.name.lastIndexOf('.') + 1)
    const imgType = ['xls', 'xlsx']
    const data = { message: '', check: true, }
    if (imgType.indexOf(testmsg) < 0) {  //根据后缀，判断是否符合图片格式
        data.message = '暂不支持此excel文件格式'
        data.check = false
        return data
    }
    return data
}
export function checkPdfType(file) {
    let testmsg = file.name.substring(file.name.lastIndexOf('.') + 1)
    const imgType = ['pdf']
    const data = { message: '', check: true, }
    if (imgType.indexOf(testmsg) < 0) {  //根据后缀，判断是否符合图片格式
        data.message = '请使用pdf文件'
        data.check = false
        return data
    }
    return data
}

export function checkWrdType(file) {
    let testmsg = file.name.substring(file.name.lastIndexOf('.') + 1)
    const imgType = ['doc', 'docx']
    const data = { message: '', check: true, }
    if (imgType.indexOf(testmsg) < 0) {  //根据后缀，判断是否符合图片格式
        data.message = '请使用word文件'
        data.check = false
        return data
    }
    return data
}

export function createUniqueString() {
    const timestamp = +new Date() + '';
    const randomNum = parseInt((1 + Math.random()) * 65536) + '';
    return (+(randomNum + timestamp)).toString(32);
}


/**
 * @param {Sting} url
 * @param {Sting} title
 * @param {Number} w
 * @param {Number} h
 */

export function openWindow(url, title, w, h) {
    // Fixes dual-screen position                         Most browsers      Firefox
    const dualScreenLeft = window.screenLeft !== undefined ? window.screenLeft : screen.left;
    const dualScreenTop = window.screenTop !== undefined ? window.screenTop : screen.top;

    const width = window.innerWidth ? window.innerWidth : document.documentElement.clientWidth ? document.documentElement.clientWidth : screen.width;
    const height = window.innerHeight ? window.innerHeight : document.documentElement.clientHeight ? document.documentElement.clientHeight : screen.height;

    const left = ((width / 2) - (w / 2)) + dualScreenLeft;
    const top = ((height / 2) - (h / 2)) + dualScreenTop;
    const newWindow = window.open(url, title, 'toolbar=no, location=no, directories=no, status=no, menubar=no, scrollbars=no, resizable=yes, copyhistory=no, width=' + w + ', height=' + h + ', top=' + top + ', left=' + left);

    // Puts focus on the newWindow
    if (window.focus) {
        newWindow.focus();
    }
}


export const getQueryString = (name) => {
  const reg = new RegExp(`(^|&)${name}=([^&]*)(&|$)`, 'i');
  const r = window.location.search.substr(1).match(reg) || window.location.hash.substring((window.location.hash.search(/\?/)) + 1).match(reg);
  if (r != null) {
    return unescape(r[2]);
  }
  return '';
};
