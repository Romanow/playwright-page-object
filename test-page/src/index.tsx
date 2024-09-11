import React from "react";
import ReactDOM from "react-dom/client";
import MainPage from "./MainPage";

import "bootstrap/dist/css/bootstrap.min.css";

const element = document.getElementById("root") as HTMLElement;
const root = ReactDOM.createRoot(element);
root.render(<React.StrictMode><MainPage/></React.StrictMode>);
