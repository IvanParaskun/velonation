let options = {
    root: null,
    rootMargin: "0px",
    threshold: 0
}

export const lazyLoad = (image, src) => {
    const loaded = () => {
        image.style.opacity = "1"
    }

    const observer = new IntersectionObserver(entries => {
        if (entries[0].isIntersecting) {
            image.src = src
            if (image.complete) {
                loaded()
            } else {
                image.addEventListener('load', loaded)
            }
        }
    }, options)

    observer.observe(image)

    return {
        destroy() {
            image.removeEventListener('load', loaded)
        }
    }
}

export const serializeForm = (form) => {
    let serialized = {}

    for (let [key, value] of form)
        if (key != 'file')
            serialized[key] = value

    return serialized
}

export const transformViolations = (violations) => {
    let v = {}

    for (let { fieldName, errorMessage } of violations)
        v[fieldName] = errorMessage

    return v
}