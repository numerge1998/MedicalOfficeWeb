import React from "react";
import Table from "../../commons/tables/table";
import * as API_USERS from "../../patient/api/patient-api";

class PatientTable extends React.Component {

    constructor(props) {
        super(props);
        this.reloadHandler = this.props.reloadHandler;
        this.handleDelete = this.handleDelete.bind(this);
        this.handleUpdate = this.handleUpdate.bind(this);
        this.toggle1 = this.props.toggle1;
        this.state = {
            tableData: this.props.tableData
        };
    }

    columns = [
        {
            Header: 'Id',
            accessor: 'id',
        },
        {
            Header: 'Name',
            accessor: 'name',
        },
        {
            Header: 'Address',
            accessor: 'address',
        },
        {
            Header: 'Birth_date',
            accessor: 'birth_date',
        },
        {
            Header: 'Gender',
            accessor: 'gender',
        },
        {
            Header: 'Delete',
            accessor: 'id',
            Cell:cell=>(<button class="btn btn-danger"  onClick={()=>{this.handleDelete(cell.value)}} >Delete</button>)
        },
        {
            Header: 'Update',
            accessor: 'id',
            Cell:cell=>(<button class="btn btn-primary" onClick={()=>{this.handleUpdate(cell.value)}} >Update</button>)
        }
    ];

    filters = [
        {
            accessor: 'name',
        }
    ];

    handleDelete(id) {
        if (window.confirm('Are you sure?')) {
            return API_USERS.deletePatient(id, (result, status, error) => {
                if (result !== null && (status === 200 || status === 201)) {
                    console.log("Successfully deleted patient with id: " + result);
                    this.reloadHandler();
                } else {
                    this.setState(({
                        errorStatus: status,
                        error: error
                    }));
                }
            });
        }
    }

    handleUpdate(id){
        this.props.id(id);
        this.toggle1();
        return id;
    }

    render() {
        return (
            <Table
                data={this.state.tableData}
                columns={this.columns}
                search={this.filters}
                pageSize={5}
                onChange = {this.handleUpdate}
            />
        )
    }
}

export default PatientTable;