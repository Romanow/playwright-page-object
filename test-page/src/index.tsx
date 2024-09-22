import React from "react";
import ReactDOM from "react-dom/client";
import MainPage from "./pages/MainPage";

import "bootstrap/dist/css/bootstrap.min.css";
import "bootstrap/dist/js/bootstrap.min"

const element = document.getElementById("root") as HTMLElement;
const root = ReactDOM.createRoot(element);
root.render(<React.StrictMode><MainPage/></React.StrictMode>);
