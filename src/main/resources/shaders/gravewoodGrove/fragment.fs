#ifdef GL_ES
precision lowp float;
#endif

varying vec2 v_texCoord;
uniform sampler2D u_texture;

void main() {
    vec4 texColor = texture2D(u_texture, v_texCoord);

    float average = (texColor.r + texColor.g + texColor.b) / 3.0;
    vec4 grayColor = vec4(average, average, average, texColor.a);

    gl_FragColor = mix(texColor, grayColor, 0.55);
}