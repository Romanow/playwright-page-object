import React, {FC} from "react";
import {byCss, byXpath, byTestId, byRoleAndText, withParent} from "../utils/snippet";

const FunctionsDialog: FC<{ id: string }> = ({id}) => {
    return (
        <div id={id}
             data-testid={`${id}-dialog`}
             className="modal fade"
             tabIndex={-1}
             aria-labelledby={`${id}-header`}
             aria-hidden="true">
            <div className="modal-dialog modal-dialog-centered modal-lg modal-dialog-scrollable">
                <div className="modal-content">
                    <div className="modal-header">
                        <h1 className="modal-title fs-5" id={`${id}-header`}>Использование</h1>
                        <button type="button"
                                data-testid={`close-${id}-dialog`}
                                className="btn-close"
                                data-bs-dismiss="modal"
                                aria-label="Close"/>
                    </div>
                    <div className="modal-body">
                        <b>ComponentFactory</b> – фабрика для создания <i>Page Object</i>.
                        Принимает на вход <code>Page</code> (объект Playwright) и класс, наследуемый
                        от <code>PageObject</code>,
                        который нужно создать.<br/>
                        С помощью reflection обходит аннотации <code>@FindBy</code> и <code>@Component</code>.
                    </div>
                    <div className="card m-3">
                        <div className="card-body">
                            <ul className="nav nav-tabs" role="tablist">
                                <li className="nav-item" role="presentation">
                                    <button id="by-testid-tab"
                                            className="nav-link active"
                                            data-bs-toggle="tab"
                                            data-bs-target="#by-testid"
                                            type="button"
                                            role="tab"
                                            aria-controls="by-testid"
                                            aria-selected="true">
                                        By TestID
                                    </button>
                                </li>
                                <li className="nav-item" role="presentation">
                                    <button id="by-css-tab"
                                            className="nav-link"
                                            data-bs-toggle="tab"
                                            data-bs-target="#by-css"
                                            type="button"
                                            role="tab"
                                            aria-controls="by-css"
                                            aria-selected="false">
                                        By CSS
                                    </button>
                                </li>
                                <li className="nav-item" role="presentation">
                                    <button id="by-xpath-tab"
                                            className="nav-link"
                                            data-bs-toggle="tab"
                                            data-bs-target="#by-xpath"
                                            type="button"
                                            role="tab"
                                            aria-controls="by-xpath"
                                            aria-selected="false">
                                        By XPath
                                    </button>
                                </li>
                                <li className="nav-item" role="presentation">
                                    <button id="by-role-and-text-tab"
                                            className="nav-link"
                                            data-bs-toggle="tab"
                                            data-bs-target="#by-role-and-text"
                                            type="button"
                                            role="tab"
                                            aria-controls="by-role-and-text"
                                            aria-selected="false">
                                        By Role And Text
                                    </button>
                                </li>
                                <li className="nav-item" role="presentation">
                                    <button id="with-parent-tab"
                                            className="nav-link"
                                            data-bs-toggle="tab"
                                            data-bs-target="#with-parent"
                                            type="button"
                                            role="tab"
                                            aria-controls="with-parent"
                                            aria-selected="false">
                                        With Parent
                                    </button>
                                </li>
                            </ul>
                            <div className="tab-content" id="pills-tabContent">
                                <div className="tab-pane fade show active"
                                     id="by-testid"
                                     role="tabpanel"
                                     aria-labelledby="by-testid-tab"
                                     tabIndex={-1}>
                                    <pre><code>{byTestId}</code></pre>
                                </div>
                                <div className="tab-pane fade"
                                     id="by-css"
                                     role="tabpanel"
                                     aria-labelledby="by-css-tab"
                                     tabIndex={-1}>
                                    <pre><code>{byCss}</code></pre>
                                </div>
                                <div className="tab-pane fade"
                                     id="by-xpath"
                                     role="tabpanel"
                                     aria-labelledby="by-xpath-tab"
                                     tabIndex={-1}>
                                    <pre><code>{byXpath}</code></pre>
                                </div>
                                <div className="tab-pane fade"
                                     id="by-role-and-text"
                                     role="tabpanel"
                                     aria-labelledby="by-role-and-text-tab"
                                     tabIndex={-1}>
                                    <pre><code>{byRoleAndText}</code></pre>
                                </div>
                                <div className="tab-pane fade"
                                     id="by-role-and-text"
                                     role="tabpanel"
                                     aria-labelledby="by-role-and-text-tab"
                                     tabIndex={-1}>
                                    <pre><code>{byRoleAndText}</code></pre>
                                </div>
                                <div className="tab-pane fade"
                                     id="with-parent"
                                     role="tabpanel"
                                     aria-labelledby="with-parent-tab"
                                     tabIndex={-1}>
                                    <pre><code>{withParent}</code></pre>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default FunctionsDialog;
