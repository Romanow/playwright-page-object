import React, {FC} from "react";
import {snippet} from "./snippet";

const MainPage: FC = () => {
    return (
        <div className="container-fluid vh-100">
            <div className="row justify-content-center">
                <div className="col-lg-4 mt-5">
                    <h1 className="h1" data-testid="page-header">Тестовая страница</h1>
                    <p className="mt-3 mb-3" data-testid="description">
                        Тестовая страница для проверки реализации библиотеки <code>playwright-page-object</code>.
                        Библиотека позволяет искать элементы на странице с помощью:
                        <ul>
                            <li>Аттрибута <code>data-testid</code>.</li>
                            <li>CSS селекторов.</li>
                            <li>XPath селекторов.</li>
                            <li>По аттрибуту <code>role</code> и тексту.</li>
                            <li>А так же выполнять поиск элемента в ограниченной зоне с помощью parent.</li>
                        </ul>
                    </p>
                    <div className="card">
                        <div className="card-body">
                            <p>Пример использования</p>
                            <pre><code>{snippet}</code></pre>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default MainPage;
