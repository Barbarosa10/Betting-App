#cat "$HOME"
PROTOC_GEN_TS_PATH="$(pwd)/node_modules/.bin/protoc-gen-ts.cmd"
#cat "${PROTOC_GEN_TS_PATH}"
PROTOC_OUT_DIR="./proto/generated"
mkdir -p ${PROTOC_OUT_DIR}
"C:\Users\duciu\Downloads\protoc-3.0.0-win32\bin\protoc" \
       --plugin="protoc-gen-ts=${PROTOC_GEN_TS_PATH}" \
       --js_out="import_style=commonjs,binary:${PROTOC_OUT_DIR}" \
       --ts_out="service=grpc-web:${PROTOC_OUT_DIR}" \
       proto/identity.proto
