import React, {FC} from "react";
import {example} from "../utils/snippet";
import LicenseDialog from "../components/LicenseDialog";
import FunctionsDialog from "../components/FunctionsDialog";

const MainPage: FC = () => {
    return (
        <div className="container-fluid vh-100">
            <div className="row justify-content-center">
                <div className="col-lg-6 mt-5">
                    <h1 className="h1" data-testid="page-header">Тестовая страница</h1>
                    <p className="mt-3 mb-3" data-testid="description">
                        Тестовая страница для проверки реализации библиотеки <code>playwright-page-object</code>.
                        Библиотека позволяет искать элементы на странице с помощью:
                    </p>
                    <ul className="features">
                        <li>Аттрибута <code>data-testid</code>.</li>
                        <li>CSS селекторов.</li>
                        <li>XPath селекторов.</li>
                        <li>По аттрибуту <code>role</code> и тексту.</li>
                        <li>А так же выполнять поиск элемента в ограниченной зоне с помощью parent.</li>
                    </ul>
                    <div className="card">
                        <div className="card-body">
                            <p>Пример использования</p>
                            <pre><code>{example}</code></pre>
                        </div>
                    </div>
                    <div className="container mt-3">
                        <div className="row justify-content-around">
                            <div className="col-4">
                                <button type="button"
                                        className="btn btn-outline-primary w-100"
                                        data-bs-toggle="modal"
                                        data-bs-target="#functions">
                                    Описание функций
                                </button>
                            </div>
                            <div className="col-4">
                                <button type="button"
                                        className="btn btn-outline-secondary w-100"
                                        data-bs-toggle="modal"
                                        data-bs-target="#license">
                                    Лицензия
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <FunctionsDialog id="functions"/>
            <LicenseDialog id="license"/>
        </div>
    );
}

export default MainPage;
