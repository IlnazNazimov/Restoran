import {
    HIDE_LOADER,
    LOGIN,
    LOGIN_TOKEN,
    LOGOUT,
    SET_ORDERS,
    SET_SESSION,
    SHOW_ERROR,
    SHOW_LOADER
} from "../actions/app";
import {AppToaster} from "../../components/app/toaster";

const initialState = {
    username: "",
    token: "",
    orders: null,
    session: null,
    loaded: true
};

export default (state = initialState, action) => {
    switch (action.type) {
        case LOGIN:
            localStorage.setItem("token", action.token);
            return {...state, username: action.username, token: action.token};
        case LOGIN_TOKEN:
            return {...state, token: action.token};
        case LOGOUT:
            localStorage.removeItem("token");
            return {...state, username: null, token: null};
        case SHOW_ERROR:
            AppToaster.show({message: action.message, intent: "danger"});
            return state;
        case SET_SESSION:
            return {...state, session: action.session};
        case SHOW_LOADER:
            return {...state, loaded: false};
        case HIDE_LOADER:
            return {...state, loaded: true};
        case SET_ORDERS:
            return {...state, orders: action.orders};
        default:
            return state;
    }
}