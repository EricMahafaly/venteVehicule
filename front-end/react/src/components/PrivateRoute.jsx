import { Navigate, Route } from "react-router-dom";


const isAuthenticated = () => {
    const token = localStorage.getItem("token");
    var value = false;
    if(token != null) {
        value = true;
    }

    return value;
}

function PrivateRoute({path, element}) {
    if(isAuthenticated) {
        return <Route path={path} element={element} />
    }
    return <Navigate to="/login" />
};

export default PrivateRoute;