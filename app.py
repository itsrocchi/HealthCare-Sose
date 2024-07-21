from flask import Flask, request, jsonify, render_template
import requests
from zeep import Client
from zeep.exceptions import Fault

app = Flask(__name__)

# Define base URLs for REST services
BASE_URL_PRS = 'http://172.27.64.1:9000/prs'
BASE_URL_DRS = 'http://172.27.64.1:9000/drs'
BASE_URL_HMS = 'http://172.27.64.1:8084/hms'

# Define the URL for the SOAP service
SOAP_URL = 'http://172.27.64.1:8083/appointmentSchedulingService/ass?wsdl'
soap_client = Client(SOAP_URL)

@app.route('/')
def index():
    return render_template('index.html')

@app.route('/appointments')
def appointments():
    return render_template('appointments.html')

@app.route('/patients')
def patients():
    return render_template('patients.html')

@app.route('/doctors')
def doctors():
    return render_template('doctors.html')

@app.route('/medical_records')
def medical_records():
    return render_template('medical_records.html')

@app.route('/statistics')
def statistics():
    return render_template('statistics.html')

@app.route('/aggregation')
def aggregation():
    return render_template('aggregation.html')

@app.route('/prs', methods=['GET'])
def get_prs():
    try:
        response = requests.get(BASE_URL_PRS)
        response.raise_for_status()
        return jsonify(response.json())
    except requests.exceptions.RequestException as e:
        return str(e), 400

@app.route('/prs', methods=['POST'])
def post_prs():
    data = request.json
    try:
        response = requests.post(BASE_URL_PRS, json=data)
        response.raise_for_status()
        return jsonify(response.json())
    except requests.exceptions.RequestException as e:
        return str(e), 400

@app.route('/prs/<cf>', methods=['GET', 'PUT', 'DELETE'])
def prs_by_cf(cf):
    if request.method == 'GET':
        try:
            response = requests.get(f'{BASE_URL_PRS}/{cf}')
            response.raise_for_status()
            return jsonify(response.json())
        except requests.exceptions.RequestException as e:
            return str(e), 400
    elif request.method == 'PUT':
        data = request.json
        try:
            response = requests.put(f'{BASE_URL_PRS}/{cf}', json=data)
            response.raise_for_status()
            return jsonify(response.json())
        except requests.exceptions.RequestException as e:
            return str(e), 400
    elif request.method == 'DELETE':
        response = requests.delete(f'{BASE_URL_PRS}/{cf}')
        response.raise_for_status()
        try:
            if response.content:  # Check if there's any content in the response
                return jsonify(response.json())
            else:
                return '', 204  # No Content
        except requests.exceptions.HTTPError as http_err:
            if response.status_code == 404:
                return 'Resource not found', 404
            else:
                return str(http_err), response.status_code
        except requests.exceptions.RequestException as e:
            return str(e), 400
@app.route('/drs', methods=['GET'])
def get_drs():
    try:
        response = requests.get(BASE_URL_DRS)
        response.raise_for_status()
        return jsonify(response.json())
    except requests.exceptions.RequestException as e:
        return str(e), 400

@app.route('/drs', methods=['POST'])
def post_drs():
    data = request.json
    try:
        response = requests.post(BASE_URL_DRS, json=data)
        response.raise_for_status()
        return jsonify(response.json())
    except requests.exceptions.RequestException as e:
        return str(e), 400

@app.route('/drs/<id>', methods=['GET', 'PUT', 'DELETE'])
def drs_by_id(id):
    if request.method == 'GET':
        try:
            response = requests.get(f'{BASE_URL_DRS}/{id}')
            response.raise_for_status()
            return jsonify(response.json())
        except requests.exceptions.RequestException as e:
            return str(e), 400
    elif request.method == 'PUT':
        data = request.json
        try:
            response = requests.put(f'{BASE_URL_DRS}/{id}', json=data)
            response.raise_for_status()
            return jsonify(response.json())
        except requests.exceptions.RequestException as e:
            return str(e), 400
    elif request.method == 'DELETE':
        response = requests.delete(f'{BASE_URL_DRS}/{id}')
        response.raise_for_status()
        try:
            if response.content:  # Check if there's any content in the response
                return jsonify(response.json())
            else:
                return '', 204  # No Content
        except requests.exceptions.HTTPError as http_err:
            if response.status_code == 404:
                return 'Resource not found', 404
            else:
                return str(http_err), response.status_code
        except requests.exceptions.RequestException as e:
            return str(e), 400

@app.route('/hms/aggregatedData/<string:cf>', methods=['GET'])
def get_aggregate_data(cf):
    try:
        response = requests.get(f'{BASE_URL_HMS}/aggregatedData/{cf}')
        response.raise_for_status()
        return jsonify(response.json())
    except requests.exceptions.RequestException as e:
        return str(e), 400


@app.route('/hms/medicalRecord', methods=['POST'])
def post_medical_record():
    data = request.json
    try:
        response = requests.post(f'{BASE_URL_HMS}/medicalRecord', json=data)
        response.raise_for_status()
        if response.content:  # Check if there's any content in the response
                return jsonify(response.json())
        else:
            return '', 204  # No Content
    except requests.exceptions.RequestException as e:
        return str(e), 400

@app.route('/hms/medicalRecord/<cf>', methods=['PUT'])
def put_medical_record(cf):
    data = request.json
    try:
        response = requests.put(f'{BASE_URL_HMS}/medicalRecord/{cf}', json=data)
        response.raise_for_status()
        return jsonify(response.json())
    except requests.exceptions.RequestException as e:
        return str(e), 400

@app.route('/hms/statistical/<stat>', methods=['GET'])
def get_statistical(stat):
    try:
        response = requests.get(f'{BASE_URL_HMS}/statistical/{stat}')
        response.raise_for_status()
        return jsonify(response.json())
    except requests.exceptions.RequestException as e:
        return str(e), 400

@app.route('/soap/<operation>', methods=['POST'])
def soap_operation(operation):
    data = request.json
    if operation == 'cancelAppointment':
        response = soap_client.service.cancelAppointment(data['arg0'])
    elif operation == 'getAllAppointments':
        response = soap_client.service.getAllAppointments()
    elif operation == 'getAppointmentById':
        response = soap_client.service.getAppointmentById(data['arg0'])
    elif operation == 'getAppointmentsByDoctor':
        response = soap_client.service.getAppointmentsByDoctor(data['arg0'])
    elif operation == 'getAppointmentsByPatient':
        response = soap_client.service.getAppointmentsByPatient(data['arg0'])
    elif operation == 'scheduleAppointment':
        response = soap_client.service.scheduleAppointment(data['arg0'], data['arg1'], data['arg2'], data['arg3'], data['arg4'], data['arg5'])
    elif operation == 'updateAppointment':
        response = soap_client.service.updateAppointment(data['arg0'], data['arg1'])
    return jsonify(response)

if __name__ == '__main__':
    app.run(debug=True)
