from flask import request
from flask_restful import Resource
from fb import client
import hashlib
import os


class Account(Resource):
    def post(self):
        id = request.form['id']
        pw = request.form['pw']

        session = client.login(id, pw)

        if session:
            h = hashlib.sha256()
            ip = request.remote_addr
            h.update(ip.encode('utf-8'))
            session.saveSession(h.hexdigest())
            return '', 201
        else:
            return '', 204

    def get(self):
        ip = request.remote_addr
        h = hashlib.sha256()
        h.update(ip.encode('utf-8'))
        if os.path.isfile(h.hexdigest()):
            return '', 200
        else:
            return '', 204

    def delete(self):
        # logout
        ip = request.remote_addr
        h = hashlib.sha256()
        h.update(ip.encode('utf-8'))
        os.remove(h.hexdigest())
