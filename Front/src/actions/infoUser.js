const URL_BASE = 'https://back-aplicacion-empresarial.herokuapp.com';   

export const LOADING = 'LOADING'
export const LOADED_SUCCESS = 'LOADED_SUCCESS'
export const LOADED_FAILURE = 'LOADED_FAILURE'

export const loading = () => ({ 
    type: LOADING 
})

export const success = payload => ({
    type: LOADED_SUCCESS,
    payload
});

export const failure = () => ({ 
    type: LOADED_FAILURE 
})

export function getInfoUser(userId) {
    return async dispatch => {
        dispatch(loading())
        try {
            const response = await fetch(`${URL_BASE}/getInfoUser/${userId}`)
            const data = await response.json()
            dispatch(success({ infoUser: data, redirect: `/profile` }))
        } catch (error) {
            dispatch(failure())
        }
    }
}

export function updateInfoUser(infoUser) {
    return async dispatch => {
        dispatch(loading())
        try {
            const response = await fetch(`${URL_BASE}/updateInfoUser`,
                {
                    method: 'PUT',
                    mode: 'cors',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify(infoUser)
                }
            )
            const id = await response.text()
            dispatch(success({successful: "Cambio realizado con éxito"}));
        } catch (error) {
            dispatch(failure({error: "A ocurrido un problema"}))
        }
    }
}