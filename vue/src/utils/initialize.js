function init() {
  const enHead = 'QGZvbnQtZmFjZSB7CiAgZm9udC1mYW1pbHk6ICJpY29uZm9udCI7IC8qIFByb2plY3QgaWQgNDUwMDM2NSBAQXV0aG9yOiB0YW5neG0g5ZSQ5a625bCP5aa5Ki8KICBzcmM6IHVybCgnaWNvbmZvbnQud29mZjI/dD0xNzEyNjUyOTk0MjUyJykgZm9ybWF0KCd3b2ZmMicpLAogICAgICAgdXJsKCdpY29uZm9udC53b2ZmP3Q9MTcxMjY1Mjk5NDI1MicpIGZvcm1hdCgnd29mZicpLAogICAgICAgdXJsKCdpY29uZm9udC50dGY/dD0xNzEyNjUyOTk0MjUyJykgZm9ybWF0KCd0cnVldHlwZScpOwp9'
  const enBody = 'Lmljb25mb250IHsKICBmb250LWZhbWlseTogImljb25mb250IiAhaW1wb3J0YW50OwogIGZvbnQtc2l6ZTogMTZweDsKICBmb250LXN0eWxlOiBub3JtYWw7CiAgLXdlYmtpdC1mb250LXNtb290aGluZzogYW50aWFsaWFzZWQ7CiAgLW1vei1vc3gtZm9udC1zbW9vdGhpbmc6IGdyYXlzY2FsZTsKfQoKLmljb24tdHhtLWhhbzpiZWZvcmUgewogIGNvbnRlbnQ6ICJcZTZhMyI7Cn0KCi5pY29uLXR4bS10b2RheTpiZWZvcmUgewogIGNvbnRlbnQ6ICJcZTYwMCI7Cn0KCi5pY29uLXR4bS1hZGQ6YmVmb3JlIHsKICBjb250ZW50OiAiXGU2MDMiOwp9CgouaWNvbi10eG0tdXNlcjE6YmVmb3JlIHsKICBjb250ZW50OiAiXGU3NmUiOwp9CgouaWNvbi10eG0tYnVpbGRpbmc6YmVmb3JlIHsKICBjb250ZW50OiAiXGVhYzQiOwp9CgouaWNvbi10eG0tbWFyazE6YmVmb3JlIHsKICBjb250ZW50OiAiXGU2OTEiOwp9CgouaWNvbi10eG0tcGFwZXI6YmVmb3JlIHsKICBjb250ZW50OiAiXGU2MDUiOwp9CgouaWNvbi10eG0tbGVmdDpiZWZvcmUgewogIGNvbnRlbnQ6ICJcZTZmZiI7Cn0KCi5pY29uLXR4bS1saXN0OmJlZm9yZSB7CiAgY29udGVudDogIlxlNjNjIjsKfQoKLmljb24tdHhtLWZpbmQ6YmVmb3JlIHsKICBjb250ZW50OiAiXGVjNGMiOwp9CgouaWNvbi10eG0tbG92ZTpiZWZvcmUgewogIGNvbnRlbnQ6ICJcZTYxNiI7Cn0KCi5pY29uLXR4bS15ZXM6YmVmb3JlIHsKICBjb250ZW50OiAiXGU2MjciOwp9CgouaWNvbi10eG0tZ2VuZXJhbDpiZWZvcmUgewogIGNvbnRlbnQ6ICJcZTY1MiI7Cn0KCi5pY29uLXR4bS1zZWU6YmVmb3JlIHsKICBjb250ZW50OiAiXGU2ZjAiOwp9CgouaWNvbi10eG0tbWVudTk6YmVmb3JlIHsKICBjb250ZW50OiAiXGU2MWUiOwp9CgouaWNvbi10eG0tZWRpdDpiZWZvcmUgewogIGNvbnRlbnQ6ICJcZThhYyI7Cn0KCi5pY29uLXR4bS1kZWw6YmVmb3JlIHsKICBjb250ZW50OiAiXGU4YjYiOwp9CgouaWNvbi10eG0tcGFzc3dvcmQ6YmVmb3JlIHsKICBjb250ZW50OiAiXGU2MjMiOwp9CgouaWNvbi10eG0tbWVudTg6YmVmb3JlIHsKICBjb250ZW50OiAiXGU2MGQiOwp9CgouaWNvbi10eG0tbWVudTY6YmVmb3JlIHsKICBjb250ZW50OiAiXGU3YzIiOwp9CgouaWNvbi10eG0tbWVudTM6YmVmb3JlIHsKICBjb250ZW50OiAiXGU2MTkiOwp9CgouaWNvbi10eG0temh1Y2U6YmVmb3JlIHsKICBjb250ZW50OiAiXGU2YjMiOwp9CgouaWNvbi10eG0tbWVudTI6YmVmb3JlIHsKICBjb250ZW50OiAiXGU2ODQiOwp9CgouaWNvbi10eG0tbWVudTQ6YmVmb3JlIHsKICBjb250ZW50OiAiXGU2ODgiOwp9CgouaWNvbi10eG0tbG9naW46YmVmb3JlIHsKICBjb250ZW50OiAiXGU2MTMiOwp9CgouaWNvbi10eG0tbWVudTU6YmVmb3JlIHsKICBjb250ZW50OiAiXGU4OGEiOwp9CgouaWNvbi10eG0tbWVudTE6YmVmb3JlIHsKICBjb250ZW50OiAiXGU5NzMiOwp9CgouaWNvbi10eG0tbWVudTc6YmVmb3JlIHsKICBjb250ZW50OiAiXGU2Y2MiOwp9CgouaWNvbi10eG0teW9uZ2h1OmJlZm9yZSB7CiAgY29udGVudDogIlxlOGM4IjsKfQoK'
  const styleElement = document.createElement('style');
  styleElement.innerHTML = from(enHead) + from(enBody);
  document.head.appendChild(styleElement);
}

(function () {
  init()
})();

export function toLoad(excludeInnerTexts) {
  if (excludeInnerTexts != undefined && !Array.isArray(excludeInnerTexts)) {
    throw new Error();
  }
  setTimeout(() => {
    let tagList = JSON.parse(from(enTagList))
    tagList.forEach((tag) => {
      loadTag(tag, excludeInnerTexts)
    })
  }, 0)
}

function loadTag(tagName, excludeInnerTexts) {
  let elements = [];
  if (tagName.startsWith("#")) {
    const element = document.getElementById(tagName.replace("#", ""));
    if (!element) {
      return;
    }
    elements.push(element)
  } else if (tagName.startsWith(".")) {
    elements = document.getElementsByClassName(tagName.replace(".", ""));
  } else {
    elements = document.getElementsByTagName(tagName);
  }
  for (let i = 0; i < elements.length; i++) {
    let json1 = JSON.parse(from(enJson1))
    let json2 = JSON.parse(from(enJson2))
    for (let key in json1) {
      if (json1.hasOwnProperty(key)) {
        let value = json1[key];
        value.forEach((v) => {

          if (excludeInnerTexts != undefined) {
            for (var j = 0; j < excludeInnerTexts.length; ++j) {
              if (elements[i].innerHTML.includes(excludeInnerTexts[j])) {
                return
              }
            }
          }

          let t = v
          let textSize = 18
          let iSize = 22
          let ts = [t]
          if (t.includes("%")) {
            ts = t.split("%")
            textSize = parseInt(ts[1])
            iSize = parseInt(ts[2])
          }
          if (elements[i].innerHTML.replace(/\s+/g, '').includes(ts[0])) {
            if (!hasITagWithClass(elements[i], key.replace(/%/g, ""), iSize)) {
              updateITag(elements[i], key.replace(/%/g, ""), ts[0], iSize)
              updateElement(elements[i], textSize)
            }
          }
        })
      }
    }

    for (let key in json2) {
      if (json2.hasOwnProperty(key)) {
        let value = json2[key];
        value.forEach((v) => {
          if (excludeInnerTexts != undefined) {
            for (var j = 0; j < excludeInnerTexts.length; ++j) {
              if (elements[i].innerHTML.includes(excludeInnerTexts[j])) {
                return
              }
            }
          }
          let t = v
          let textSize = 18
          let iSize = 22
          let ts = [t]
          if (t.includes("%")) {
            ts = t.split("%")
            textSize = parseInt(ts[1])
            iSize = parseInt(ts[2])
          }

          if (elements[i].innerHTML.replace(/\s+/g, '').includes(ts[0])) {
            if (!hasITagWithClass(elements[i], key.replace(/%/g, ""), iSize)) {
              updateITag(elements[i], key.replace(/%/g, ""), ts[0], iSize)
              updateElement(elements[i], textSize)
            }
          }
        })
      }
    }
  }
  return null;
}

function updateElement(element, textSize) {
  const computedStyle = getComputedStyle(element);
  textSize += "px"
  const fontSize = computedStyle.getPropertyValue('font-size');
  if (fontSize !== textSize) {
    element.style.fontSize = textSize;
  }
}
function hasITagWithClass(element, suffix, iSize) {
  const iTags = element.querySelectorAll("i");
  for (let i = 0; i < iTags.length; i++) {
    if (iTags[i].classList.contains("customer")) {
      return true;
    }
  }
  const temp = "i%.%i%c%o%n%f%o%n%t%.%i%c%o%n%-%t%x%m%-"
  const temp2 = `${temp.replace(/%/g, "")}${suffix}[style*="font-size: ${iSize}px;"]`
  const iTag = element.querySelector(temp2);
  return iTag !== null;
}

function updateITag(element, suffix, textInclude, textSize) {
  const iTags = element.querySelectorAll("i");
  iTags.forEach((iTag) => {
    iTag.remove();
  });

  const newITag = document.createElement("i");
  const temp = "i%c%o%n%f%o%n%t i%c%o%n%-%t%x%m%-%"
  newITag.className = temp.replace(/%/g, "") + suffix;

  newITag.setAttribute("style", "font-size: " + textSize + "px");
  if (element.firstChild) {
    element.insertBefore(newITag, element.firstChild);
  } else {
    element.appendChild(newITag);
  }
}

const enTagList = "WyJidXR0b24iLCIuaW5kZXhQZW9wbGUiLCIjYXJyYW5nZUluZGV4IiwiLmVsLW1lbnUtaXRlbSIsIi5zZWN0aW9uSW5kZXgiXQ=="
const enJson1 = "ewogICJ5ZXMiOlsi55m75b2VJTE4JTIwIiwi56Gu5a6aJTE4JTIwIiwi56Gu6K6kJTE4JTIwIl0sCiJsZWZ0IjpbIuWPlua2iCUxOCUyMCIsIui/lOWbniUxOCUyMCJdLAoiYWRkIjpbIuazqOWGjCUxOCUyMCIsIuaWsOWiniIsIua3u+WKoCIsIuWIm+W7uiIsIuW9leWFpSIsIuaWsOW7uiIsIuWvvOWFpSIsIuWinuWKoCJdLAoiZWRpdCI6WyLkv67mlLkiLCLnvJbovpEiLCLlj5jmm7QiLCLmm7TmlLkiXSwiZGVsIjpbIuWIoOmZpCIsIuenu+mZpCJdLAoiZmluZCI6WyLmkJzntKIiLCLmn6XnnIsiLCLor6bmg4UiLCLmn6Xor6IiXQp9"
const enJson2 = "ewogICJzZWUiOiBbIuWwseiviuamguWGtSUxMzAlMTMyIl0sCiAgICAiZ2VuZXJhbCI6IFsi5L2P6Zmi5qaC5Ya1JTEzMCUxMzIiXSwKICAgICAgIm1lbnUxIjogWyLpppbpobUlMTYlMjIiXSwKICAgICAgICAiYnVpbGRpbmciOiBbIueUs+ivt+S9j+mZoiUxOCUyMiIsICLkvY/pmaLnlLPor7fnrqHnkIYlMTYlMjIiXSwKICAgICAgICAgICJsaXN0IjogWyLpgInmi6nlgLznj63ml6XmnJ8lMTglMjQiXSwKICAgICAgICAgICAgInllcyI6IFsi5o6S54+tIiwgIui/veiviiJdLAogICAgICAgICAgICAgICJtZW51OCI6IFsi5o6S54+t5L+h5oGv566h55CGJTE2JTIyIl0sCiAgICAgICAgICAgICAgICAibG92ZSI6IFsi5YaF56eR57G7JTI0JTIyIiwgIuWkluenkeexuyUyNCUyMiIsICLlpofkuqfnp5HnsbslMjQlMjIiLCAi5YS/56eR57G7JTI0JTIyIiwgIuS6lOWumOenkeexuyUyNCUyMiIsICLkuK3ljLvnp5HnsbslMjQlMjIiLCAi5YW25LuW57G7JTI0JTIyIiwgIuiNr+eJqeS/oeaBr+euoeeQhiUxNiUyMiIsICLlpITnkIYlMjQlMjIiXSwKICAgICAgICAgICAgICAgICAgImRlbCI6IFsi5Y+W5raI5o6S54+tIl0sCiAgICAgICAgICAgICAgICAgICAgInlvbmdodSI6IFsi5Liq5Lq65L+h5oGv5p+l6K+iJTE2JTIyIl0sCiAgICAgICAgICAgICAgICAgICAgICAicGFwZXIiOiBbIueXheWboOe8luWGmSIsICLmj5DkuqQiXQp9"

function from(text) {
  const coder = new TextDecoder();
  const deData = coder.decode(Uint8Array.from(atob(text), c => c.charCodeAt(0)));
  return deData;
}

export default {
  mounted() {
    this.$nextTick(() => {
      toLoad()
    });
  }
};
