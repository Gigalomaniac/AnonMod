uniform sampler2D u_texture;

varying vec4 v_color;
varying vec2 v_texCoord;

float rand(vec2 co){
    return fract(sin(dot(co, vec2(12.9898, 78.233))) * 43758.5453);
}

void main() {
    vec4 texColor = texture2D(u_texture, v_texCoord);
    float dmin = 200.0 * 200.0;
    float dmax = 240.0 * 240.0;
    vec2 screenCoord = gl_FragCoord.xy;

        vec2 texDiff = vec2(rand(screenCoord), rand(screenCoord + vec2(2000.0, 2000.0)));
        texColor = texture2D(u_texture, v_texCoord + texDiff / vec2(1920.0, 1080.0) * 0.5);
        gl_FragColor = vec4(texColor.rgb * 0.8, 1.0);

}
