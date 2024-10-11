//---------------------------------------------------------------------------

varying vec2 pos;                    // screen position <-1,+1>
                                // fragment output color
uniform sampler2D txr;          // texture to blu

uniform float sizeX;
uniform float sizeY;
uniform float stepSize;
uniform float subDiv;
uniform float thresholdDivider;
uniform float whitening;
uniform float darkening;
uniform float alphaMult;

//---------------------------------------------------------------------------
void main()
{
    vec2 xStep = vec2(1./sizeX, 0.) * stepSize;
    vec2 yStep = vec2(0.,1./sizeY) * stepSize;

    vec4 selfColor = texture2D(txr, pos);

    float unitSquared = stepSize*stepSize;
    float radius = subDiv*subDiv*unitSquared;
    float count = 1.;

    if (selfColor.a > 0.) {
        gl_FragColor = vec4(whitening + selfColor.r * darkening,
                            whitening + selfColor.g * darkening,
                            whitening + selfColor.b * darkening,
                            (whitening + selfColor.a) * alphaMult/((selfColor.r+selfColor.g+selfColor.b)/3.));
        return;
    }

    for (int i = -int(subDiv); i <= int(subDiv); i++) {
        for (int j = -int(subDiv); j <= int(subDiv); j++) {
            if (float(i*i)*unitSquared + float(j*j)*unitSquared < radius) {
                vec4 color = texture2D(txr, pos + float(i)*xStep + float(j)*yStep);
                count += color.a;
            }
        }
    }

    gl_FragColor = vec4(step(subDiv*subDiv/thresholdDivider, count));
}