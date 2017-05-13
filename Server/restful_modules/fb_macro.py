from flask import request
from flask_restful import Resource
from fb import client
import hashlib


class Account(Resource):
    def post(self):
        id = request.form['id']
        pw = request.form['pw']

        session = client.login(id, pw)

        if session:
            h = hashlib.sha256()
            h.update(id.encode('utf-8'))
            session.saveSession(h.hexdigest())
            return '', 201
        else:
            return '', 204
