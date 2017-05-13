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
            session.saveSession(h.update(id))
            return '', 201
        else:
            return '', 204
