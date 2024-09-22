import React, {FC} from "react";
import {license} from "../utils/snippet";

const LicenseDialog: FC<{ id: string }> = ({id}) => {
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
                        <h1 className="modal-title fs-5" id={`${id}-header`}>Лицензия</h1>
                        <button type="button"
                                data-testid={`close-${id}-dialog`}
                                className="btn-close"
                                data-bs-dismiss="modal"
                                aria-label="Close"/>
                    </div>
                    <div className="modal-body">
                        <pre><code>{license}</code></pre>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default LicenseDialog;
